import java.util.concurrent.Callable;


public abstract class Job implements Callable<Integer>{

	 abstract void executeJob();
	 abstract int getResult();
	abstract void setScheduler(JobScheduler jobScheduler);
	public Integer call() throws Exception {
		executeJob();
		return getResult();
	}

	}
