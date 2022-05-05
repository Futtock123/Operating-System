import java.lang.Integer;

public class Scheduler1 
{
	//This function counts the number of processes that are in the array
	//in order to avoid long, drawn out traversals.
	public static int numOfProc(PCB[] pcbA)
	{
		int nProcesses = 0;
		for (int i = 0; i < pcbA.length; i++)
		{
			if ( pcbA[i].getName() != null && pcbA[i].getArrivalTime() >= 0 && pcbA[i].getBurstTime() >= 0 )
				nProcesses++;
		}
		
		return nProcesses;
	}
	
	//Calculates the average turn around time.
	public static float avgTAT(PCB[] pcbA, int pcbSize)
	{
		float avgTAT = 0;
		float temp = 0;
		for (int i = 0; i < pcbSize; i++)
		{
			temp = temp + pcbA[i].getTurnAroundTime();
		}
		
		Integer n = new Integer(pcbSize);
		float size = n.floatValue();
		avgTAT = temp / size;
		return avgTAT;
	}
	
	//Calculates the average wait time.
	public static float avgWT(PCB[] pcbA, int pcbSize)
	{
		float avgWT = 0;
		float temp = 0;
		for (int i = 0; i < pcbSize; i++)
		{
			temp = temp + pcbA[i].getWaitTime();
		}
		
		Integer n = new Integer(pcbSize);
		float size = n.floatValue();
		avgWT = temp / size;
		return avgWT;
	}
	
	//Runs the FCFS algorithm from the FCFS class.
	public void runFCFS(PCB[] pcbA, int pcbSize)
	{
		FCFS fifo = new FCFS();
		fifo.run(pcbA, pcbSize);
	}
	
	//Runs the SJF algorithm from the SJF class.
	public void runSJF(PCB[] pcbA, int pcbSize)
	{
		SJF sj = new SJF();
		sj.run(pcbA, pcbSize);
	}
	
	//Runs the RR algorithm from the RoundRobin class.
	public void runRR(PCB[] pcbA, int pcbSize, float tQ)
	{
		RoundRobin rr = new RoundRobin();
		rr.run(pcbA, pcbSize, tQ);
	}
	
	//Runs the SRTN algorithm from the SRTN class.
	public void runSRTN(PCB[] pcbA, int pcbSize)
	{
		SRTN srt = new SRTN();
		srt.run(pcbA, pcbSize);
	}
}