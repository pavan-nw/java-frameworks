import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public abstract class JobScheduler {
	List<Job> jobs = new ArrayList<Job>();
	private int completedJobs;

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public void AddAllJob(List<Job> jobs) {

		for (Job job : jobs) {
			job.setScheduler(this);
			this.jobs.add(job);
		}
	}

	public void executeAll() {
		for (Job j : jobs) {
			j.executeJob();
		}
	}

	public synchronized void onJobComplete(Job job) {

		completedJobs++;
		/*if(job instanceof JobA){
			System.out.println("JobA Completed");
		} else if(job instanceof JobB){
			System.out.println("JobB Completed");
		}*/
		

		if (completedJobs == jobs.size()) {
			System.out.println("Callback OnAllJobsCompleted");
			onAllJobCompleteCallback(jobs);
		}

	}

	public abstract void onAllJobCompleteCallback(List<Job> jobs);

	public void executeAllParallel() {
		ExecutorService exec = Executors.newFixedThreadPool(2);
		List<Future<Integer>> results = null;
		try {
			 results = exec.invokeAll(jobs);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		exec.shutdown();
		
		if(results!=null){
		for(Future<Integer> res : results){
			
			try {
				if(res.get()!=null){
					completedJobs++;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (completedJobs == jobs.size()) {
				System.out.println("Callback OnAllJobsCompleted");
				onAllJobCompleteCallback(jobs);
			}
		}
		}
		 
	}

}
