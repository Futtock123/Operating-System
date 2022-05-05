public class SJF 
{
	public void run(PCB[] pcbA, int pcbSize)
	{
		int tot = 0;						//Tracks the number of completed processes.
		int t = 0;							//Tick counter.
		float st = 0;						//Used to keep track of the time.
		float min = 999;					//Used to keep track of the minimum burst time.
		for (int c = 0; c < pcbSize; c++)	//Sets executed flags to 0.
			pcbA[c].setExecuted(0);
		
		while (tot < pcbSize)
		{
			min = 999;
			t = pcbSize;
			for (int i = 0; i < pcbSize; i++)
			{
				//Checks if a process has arrived and makes sure that the process has not been executed yet.
				if (pcbA[i].getArrivalTime() <= st && pcbA[i].getExecuted() == 0 && pcbA[i].getBurstTime() < min)
				{
					min = pcbA[i].getBurstTime();
					t = i;
				}
			}
			
			//Increments the time.
			if (t == pcbSize)
				st++;
			else
			{
				//Sets the completion time of the current process equal to the sum of the current time plus the 
				//process's burst time.
				pcbA[t].setCompletionTime(st + pcbA[t].getBurstTime());
				st += pcbA[t].getBurstTime();
				//Calculates the turn around time and wait time.
				pcbA[t].setTurnAroundTime(pcbA[t].getCompletionTime() - pcbA[t].getArrivalTime());
				pcbA[t].setWaitTime(pcbA[t].getTurnAroundTime() - pcbA[t].getBurstTime());
				//Sets the flag of the process to complete and increments the completed processes counter.
				pcbA[t].setExecuted(1);
				tot++;
			}
		}
		//Calculates averages and prints out results.
		Scheduler1 sched = new Scheduler1();
		for (int i = 0; i < pcbSize; i++)
			System.out.println(pcbA[i].getName() + " AT:" + pcbA[i].getArrivalTime() + " BT:" + pcbA[i].getBurstTime() + " CT:" + pcbA[i].getCompletionTime() + " TAT:" + pcbA[i].getTurnAroundTime() + " WT:" + pcbA[i].getWaitTime() );
		System.out.println("Average TAT: " + sched.avgTAT(pcbA, pcbSize));
		System.out.println("Average WT: " + sched.avgWT(pcbA, pcbSize));
	}
}
