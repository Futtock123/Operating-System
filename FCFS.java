public class FCFS 
{
	public void run(PCB[] pcbA, int pcbSize) 
	{
		Scheduler1 sched = new Scheduler1();
		for (int i = 0; i < pcbSize; i++)
		{
			//When the first process enters, the program runs that process automatically and sets the completion time.
			if (i == 0)
				pcbA[i].setCompletionTime(pcbA[i].getArrivalTime() + pcbA[i].getBurstTime());
			else
			{
				//If the process has an arrival time that is greater than the completion time of the previous process,
				//then the program will automatically set the sum of the arrival and burst times of the current 
				//process equal to the process's completion time.
				if (pcbA[i].getArrivalTime() > pcbA[i - 1].getCompletionTime())
					pcbA[i].setCompletionTime(pcbA[i].getArrivalTime() + pcbA[i].getBurstTime());
				else
					pcbA[i].setCompletionTime(pcbA[i - 1].getCompletionTime() + pcbA[i].getBurstTime());
				//Otherwise, the completion time of the current process is set equal to the sum of its burst time
				//and the completion time of the previous process.
			}
			
			//Calculates turn around time and wait time.
			pcbA[i].setTurnAroundTime(pcbA[i].getCompletionTime() - pcbA[i].getArrivalTime());
			pcbA[i].setWaitTime(pcbA[i].getTurnAroundTime() - pcbA[i].getBurstTime());
			
			//Prints out the numbers.
			System.out.println(pcbA[i].getName() + " AT:" + pcbA[i].getArrivalTime() + " BT:" + pcbA[i].getBurstTime() + " CT:" + pcbA[i].getCompletionTime() + " TAT:" + pcbA[i].getTurnAroundTime() + " WT:" + pcbA[i].getWaitTime() );
		}
		
		//Calculates and prints the averages of turn around time and wait time.
		float avgTAT = sched.avgTAT(pcbA, pcbSize);
		float avgWT = sched.avgWT(pcbA, pcbSize);
		System.out.println("Average TAT: " + avgTAT);
		System.out.println("Average WT: " + avgWT);
	}
	
}
