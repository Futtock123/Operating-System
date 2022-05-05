public class RoundRobin 
{
	public void run(PCB pcbA[], int pcbSize, float tQ)
	{
		//Creates and execution queue array.
		PCB[] exQ = new PCB[pcbSize];
		for (int i = 0; i < exQ.length; i++)
			exQ[i] = new PCB();
		float timer = 0;			//Keeps track of the time.
		int qct = 0;				//Keeps track of number of processes in the queue
		float tickCount = 0;		//Keeps track of how long a process has been running for.
		PCB tempPCB;				//Temporary PCB variable used for switching processes around.
		int compProc = 0;			//Keeps track of the number of completed processes.
		
		//Sets remaining times of the processes equal to their burst times.
		for (int i = 0; i < pcbSize; i++)
			pcbA[i].setRemainingTime(pcbA[i].getBurstTime());
		
		while (true)
		{
			//As long as the flag is true, the while loop will continue looping.
			boolean flag = true;
			
			for (int i = 0; i < pcbSize; i++)
			{
				//Checks to see if a process has arrived.
				if (pcbA[i].getArrivalTime() == timer)
				{
					//If a process enters the queue as the current running process finishes its turn,
					//then the process that just arrived will go in front of the process that run ran.
					if (tickCount == 0 && qct > 1)
					{
						tempPCB = exQ[0];
						for (int j = 0; j < qct; j++)
						{
							exQ[j] = exQ[j+1];
						}
						exQ[qct+1] = tempPCB;
						exQ[qct] = pcbA[i];
						qct++;
					}
					else if (tickCount == 0 && qct == 1)
					{
						//If there is only one process in the queue, 
						//then that process will take another turn.
						tempPCB = exQ[0];
						exQ[0] = pcbA[i];
						exQ[1] = tempPCB;
					}
					else
					{
						//If there are no other conditions,
						//then the process simply goes to the back of the queue.
						exQ[qct] = pcbA[i];
					}
					//As a process enters, the number of processes increases.
					qct++;
				}
			}
			
			//Both the timer and the tracker increment.
			tickCount++;
			timer++;
			
			//Checks if the queue is empty.
			if (qct != 0)
			{
				//Checks if the remaining time of the current process is almost complete.
				if (exQ[0].getRemainingTime() > 1)
				{
					//Remaining time is decremented.
					exQ[0].setRemainingTime(exQ[0].getRemainingTime() - 1);
					//Checks if a process has reached it limit.
					if (tickCount == tQ)
					{
						//Resets the counter.
						tickCount = 0;
						//Checks if the queue has more than one process. If it has more than one,
						//then the process that just ran goes to the back of the queue.
						if (qct > 1)
						{
							tempPCB = exQ[0];
							for (int c = 0; c < qct; c++)
							{
								exQ[c] = exQ[c+1];
							}
							exQ[qct-1] = tempPCB;
						}
					}
				}
				else
				{
					//If the remaining time is not greater than one,
					//the remaining time of the process at the front of the queue
					//is set to zero. And the completion time of that process is set equal 
					//to the timer. The tick counter is reset and the completed processes
					//counter is incremented by one.
					exQ[0].setRemainingTime(0);
					exQ[0].setCompletionTime(timer);
					tickCount = 0;
					compProc++;
					
					//Once all processes have been completed, the flag is set to false.
					if (compProc == pcbSize)
					{
						flag = false;
					}
					
					for (int i = 0; i < pcbSize; i++)
					{
						//Matches the ending process's name with its 
						//counter part in the PCB Array. Once the program
						//finds a match, the process is taken out of the 
						//execution queue and the number of processes 
						//in the queue is decremented.
						if (exQ[0].getName() == pcbA[i].getName())
						{
							pcbA[i] = exQ[0];
							for (int j = 0; j < qct; j++)
							{
								exQ[j] = exQ[j+1];
							}
							qct--;
							i = pcbSize;
						}
					}
				}
			}
			
			//If the flag is false, the program breaks out of the while loop.
			if (flag == false)
				break;
		}
		
		//Calculates turn around time and wait time.
		for (int i = 0; i < pcbSize; i++)
		{
			pcbA[i].setTurnAroundTime(pcbA[i].getCompletionTime() - pcbA[i].getArrivalTime());
			pcbA[i].setWaitTime(pcbA[i].getTurnAroundTime() - pcbA[i].getBurstTime());
		}
		
		Scheduler1 sched = new Scheduler1();
		//Calculates averages and prints out numbers.
		for (int i = 0; i < pcbSize; i++)
			System.out.println(pcbA[i].getName() + " AT:" + pcbA[i].getArrivalTime() + " BT:" + pcbA[i].getBurstTime() + " CT:" + pcbA[i].getCompletionTime() + " TAT:" + pcbA[i].getTurnAroundTime() + " WT:" + pcbA[i].getWaitTime() );
		System.out.println("Average TAT: " + sched.avgTAT(pcbA, pcbSize));
		System.out.println("Average WT: " + sched.avgWT(pcbA, pcbSize));
	}
}
