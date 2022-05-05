public class SRTN 
{
	public void run(PCB pcbA[], int pcbSize)
	{
		//Creates an execution queue.
		PCB[] exQ = new PCB[pcbSize];
		for (int i = 0; i < exQ.length; i++)
			exQ[i] = new PCB();
		PCB tempPCB;			//Temporary PCB variable used for switching processes around.
		float timer = 0;		//Used to keep track of time.
		int compProc = 0;		//Used to keep track of the number of completed processes.
		//Sets all remaining times of each process equal to their burst times.
		for (int i = 0; i < pcbSize; i++)
			pcbA[i].setRemainingTime(pcbA[i].getBurstTime());
		int qct = 0;			//Used to keep track of the number of processes in the execution queue.
		
		while (true)
		{
			//Creates and set a flag to false.
			boolean flag = false;
			
			for (int i = 0; i < pcbSize; i++)
			{
				//Checks if a process has arrived.
				if (pcbA[i].getArrivalTime() == timer && pcbA[i].getBurstTime() > 0)
				{
					//If a process has arrived,
					//it is placed into the queue and 
					//the queue count is incremented.
					exQ[qct] = pcbA[i];
					qct++;
					
					//Checks if there is more than one process in the queue.
					if (qct > 1)
					{
						//Uses bubble sort to sort the array.
						for (int j = 0; j < qct - 1; j++)
						{
							for (int c = 0; c < qct - j - 1; c++)
							{
								if (exQ[c].getRemainingTime() > exQ[c+1].getRemainingTime())
								{
									tempPCB = exQ[c];
									exQ[c] = exQ[c+1];
									exQ[c+1] = tempPCB;
								}
							}
						}
					}
				}
			}
			//Increments timer and decrements remaining time of the current process.
			timer++;
			exQ[0].setRemainingTime(exQ[0].getRemainingTime() - 1);
			
			//Checks if the currently running process has completed.
			if (exQ[0].getRemainingTime() == 0)
			{
				for (int j = 0; j < pcbSize; j++)
				{
					//Searches the PCB Array for the corresponding name.
					if (pcbA[j].getName() == exQ[0].getName())
					{
						//Once the program finds a name that matches,
						//it sets the completion time equal to the current
						//time. And then increments the counter for number
						//of processes completed.
						pcbA[j].setCompletionTime(timer);
						compProc++;
					}
				}
				//Moves each of the processes over.
				for (int c = 0; c < qct; c++)
				{
					exQ[c] = exQ[c+1];
				}
				//Decrements the queue counter.
				qct--;
			}
			
			//Once all processes have been completed,
			//the flag is set to true. If the flag
			//is true, then the program breaks
			//out of the while loop.
			if (compProc == pcbSize)
				flag = true;
			
			if (flag)
				break;
		}
		//Calculates the turn around time and wait time.
		for (int i = 0; i < pcbSize; i++)
		{
			pcbA[i].setTurnAroundTime(pcbA[i].getCompletionTime() - pcbA[i].getArrivalTime());
			pcbA[i].setWaitTime(pcbA[i].getTurnAroundTime() - pcbA[i].getBurstTime());
		}
		
		Scheduler1 sched = new Scheduler1();
		//Calculates averages and prints out the numbers
		for (int i = 0; i < pcbSize; i++)
			System.out.println(pcbA[i].getName() + " AT:" + pcbA[i].getArrivalTime() + " BT:" + pcbA[i].getBurstTime() + " CT:" + pcbA[i].getCompletionTime() + " TAT:" + pcbA[i].getTurnAroundTime() + " WT:" + pcbA[i].getWaitTime() );
		System.out.println("Average TAT: " + sched.avgTAT(pcbA, pcbSize));
		System.out.println("Average WT: " + sched.avgWT(pcbA, pcbSize));
	}
}
