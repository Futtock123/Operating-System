public class Main 
{		
	public static void main(String[] args) 
	{
		Scheduler1 sched = new Scheduler1();
		PCB[] pcbA = new PCB[5000];
		for (int i = 0; i < pcbA.length; i++)
			pcbA[i] = new PCB();
		
		//Process 1
		pcbA[0].setName("Process1");
		pcbA[0].setArrivalTime(0);
		pcbA[0].setBurstTime(3);
		
		//Process 2
		pcbA[1].setName("Process2");
		pcbA[1].setArrivalTime(2);
		pcbA[1].setBurstTime(10);
		
		//Process 3
		pcbA[2].setName("Process3");
		pcbA[2].setArrivalTime(7);
		pcbA[2].setBurstTime(7);
		
		//Process 4
		pcbA[3].setName("Process4");
		pcbA[3].setArrivalTime(8);
		pcbA[3].setBurstTime(3);
		
		//Process 5
		pcbA[4].setName("Process5");
		pcbA[4].setArrivalTime(10);
		pcbA[4].setBurstTime(2);
		
		//Finds the number of processes.
		int nProc = Scheduler1.numOfProc(pcbA);
		
		//Runs different algorithms and displays their results.
		System.out.println("Number of processes: " + nProc);
		System.out.println();
		
		System.out.println("********");
		System.out.println("FCFS");
		System.out.println("********");
		sched.runFCFS(pcbA, nProc);
		System.out.println();
		
		System.out.println("********");
		System.out.println("SJF");
		System.out.println("********");
		sched.runSJF(pcbA, nProc);
		System.out.println();
		
		System.out.println("********");
		System.out.println("RR");
		System.out.println("********");
		float tQ = 2;
		System.out.println("Time Quantum: " + tQ);
		sched.runRR(pcbA, nProc, tQ);
		System.out.println();
		
		System.out.println("********");
		System.out.println("SRTN");
		System.out.println("********");
		sched.runSRTN(pcbA, nProc);
		System.out.println();
	}
}
