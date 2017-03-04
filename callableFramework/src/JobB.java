
public class JobB extends Job {

	private int result;
	private JobScheduler scheduler;
	public void executeJob() {
		for(int i=1000;i< 2000;i++){
			System.out.println("jobB printing "+i);
		}
		
		result = 2000;
		System.out.println("Callback JobComplete");
//		scheduler.onJobComplete(this);
		
	}

	public int getResult() {
		// TODO Auto-generated method stub
		return result;
	}

	public void setScheduler(JobScheduler jobScheduler) {
		scheduler = jobScheduler;

	}

}
