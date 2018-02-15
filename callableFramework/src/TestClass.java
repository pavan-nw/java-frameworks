import java.util.ArrayList;
import java.util.List;

public class TestClass
{

    public static void main(String[] args)
    {
        List<Job> jobs = new ArrayList<Job>();
        jobs.add(new JobA());
        JobB jobB = new JobB();
        jobB.setOnJobInit(() -> System.out.println("init method"));
        jobB.setOnJobComplete(() -> System.out.println("complete method"));
        jobs.add(jobB);

        JobScheduler jscheduler = new JobScheduler()
        {
            @Override
            public void onAllJobCompleteCallback(List<Job> jobs)
            {
                for (Job job : jobs)
                {
                    System.out.println("Result :" + job.getResult());
                }
            }
        };

        jscheduler.AddAllJob(jobs);

//        jscheduler.executeAll();
        jscheduler.executeAllParallelWithBlocking();
//        jscheduler.executeAllParallelWithoutBlocking();

        System.out.println(Thread.currentThread().getName());
    }
}
