import java.util.concurrent.Callable;


public abstract class Job<T> implements Callable<T>
{
    private OnJobInit onJobInit;
    private OnJobComplete onJobComplete;

    private T result;
    private JobScheduler scheduler;

    abstract T executeJob();

    public T call() throws Exception
    {
        if (onJobInit != null) onJobInit.init();
        result = executeJob();
        scheduler.onJobComplete(this);
        if (onJobComplete != null) onJobComplete.complete();
        return result;
    }

    public void setScheduler(JobScheduler jobScheduler)
    {
        scheduler = jobScheduler;
    }

    public T getResult()
    {
        // TODO Auto-generated method stub
        return result;
    }

    public void setOnJobInit(OnJobInit onJobInit)
    {
        this.onJobInit = onJobInit;
    }

    public void setOnJobComplete(OnJobComplete onJobComplete)
    {
        this.onJobComplete = onJobComplete;
    }
}
