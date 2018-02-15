
public class JobA extends Job
{

    private int result;
    private JobScheduler scheduler;

    public void executeJob()
    {
        for (int i = 0; i < 1000; i++)
        {
            System.out.println("jobA printing " + i);
        }

        result = 1000;
        System.out.println("Callback JobComplete " + Thread.currentThread().getName());
        scheduler.onJobComplete(this);

    }

    public int getResult()
    {
        // TODO Auto-generated method stub
        return result;
    }

    public void setScheduler(JobScheduler jobScheduler)
    {
        scheduler = jobScheduler;

    }

}
