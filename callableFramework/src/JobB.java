
public class JobB extends Job<Double>
{
    public Double executeJob()
    {
        Double result = null;
        for (int i = 1000; i < 2000; i++)
        {
            System.out.println("jobB printing " + i);
        }

        result = 2000.0;

        System.out.println("Callback JobComplete " + Thread.currentThread().getName());

        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        return result;
    }
}
