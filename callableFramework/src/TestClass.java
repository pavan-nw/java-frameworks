import java.util.ArrayList;
import java.util.List;

public class TestClass {

	public static void main(String[] args) {
		
		List<Job> jobs = new ArrayList<Job>();
		jobs.add(new JobA());
		jobs.add(new JobB());
		
		JobScheduler jscheduler = new JobScheduler() {
			
			@Override
			public void onAllJobCompleteCallback(List<Job> jobs) {
				for(Job job : jobs){
					System.out.println("Result :" + job.getResult());
				}
				
			}

			@Override
			public void executeAllParallel() {
				// TODO Auto-generated method stub
				super.executeAllParallel();
				System.out.println("Overriden method...");
			}
			
			
			
		};
		
		jscheduler.AddAllJob(jobs);
		
		// jscheduler.executeAll();
		jscheduler.executeAllParallel();

	}

}
