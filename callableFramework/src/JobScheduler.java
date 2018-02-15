import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class JobScheduler
{
    List<Job> jobs = new ArrayList<Job>();
    private int completedJobs;

    public List<Job> getJobs()
    {
        return jobs;
    }

    public void setJobs(List<Job> jobs)
    {
        this.jobs = jobs;
    }

    public void AddAllJob(List<Job> jobs)
    {
        for (Job job : jobs)
        {
            job.setScheduler(this);
            this.jobs.add(job);
        }
    }

    public void executeAll()
    {
        for (Job j : jobs)
        {
            j.executeJob();
        }
    }

    public synchronized void onJobComplete(Job job)
    {
        completedJobs++;
        if (completedJobs == jobs.size())
        {
            System.out.println("Callback OnAllJobsCompleted");
            onAllJobCompleteCallback(jobs);
        }

    }

    public abstract void onAllJobCompleteCallback(List<Job> jobs);

    public void executeAllParallelWithBlocking()
    {
        List cjobs = new ArrayList<Callable>(jobs);
        ExecutorService exec = Executors.newFixedThreadPool(2);
        try
        {
            exec.invokeAll(cjobs);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        exec.shutdown();
    }

    public void executeAllParallelWithoutBlocking()
    {
        ExecutorService exec = Executors.newFixedThreadPool(2);

        for (Job job : jobs)
            exec.submit(job);

        exec.shutdown();
    }

}
