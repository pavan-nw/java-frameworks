public class JobA extends Job<Integer>
{
    @Override
    Integer executeJob()
    {
        Integer result = null;
        for (int i = 0; i < 1000; i++)
        {
            System.out.println("jobA printing " + i);
        }

        result = 1000;
        System.out.println("Callback JobComplete " + Thread.currentThread().getName());
        return result;
    }
}
