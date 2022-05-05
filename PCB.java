public class PCB 
{
	public String name;
	public float burstTime; 			//run time/CPU time
	public float arrivalTime; 			//arrival time
	public float beginTime; 			//start time
	public float remainingTime;			//time left
	public float completionTime; 		//end time
	public float waitTime; 				//wait time
	public float executed;				//time you subtract from burst time
	public float turnAroundTime;		//turn around time
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	public float getWaitTime()
	{
		return waitTime;
	}
	public void setWaitTime(float waitTime)
	{
		this.waitTime = waitTime;
	}
	
	public float getBurstTime()
	{
		return burstTime;
	}
	public void setBurstTime(float burstTime)
	{
		this.burstTime = burstTime;
	}
	
	public float getArrivalTime()
	{
		return arrivalTime;
	}
	public void setArrivalTime(float arrivalTime)
	{
		this.arrivalTime = arrivalTime;
	}
	
	public float getCompletionTime()
	{
		return completionTime;
	}
	public void setCompletionTime(float completionTime)
	{
		this.completionTime = completionTime;
	}
	
	public float getBeginTime()
	{
		return beginTime;
	}
	public void setBeginTime(float beginTime)
	{
		this.beginTime = beginTime;
	}
	
	public float getRemainingTime()
	{
		return remainingTime;
	}
	public void setRemainingTime(float remainingTime)
	{
		this.remainingTime = remainingTime;
	}
	
	public float getExecuted()
	{
		return executed;
	}
	public void setExecuted(float executed)
	{
		this.executed = executed;
	}
	
	public float getTurnAroundTime()
	{
		return turnAroundTime;
	}
	public void setTurnAroundTime(float turnAroundTime)
	{
		this.turnAroundTime = turnAroundTime;
	}
}
