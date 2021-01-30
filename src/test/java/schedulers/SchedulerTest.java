package schedulers;
import java.util.*;

public class SchedulerTest {

    public static void main(String[] args){
        Scheduler scheduler1 = new EDFScheduler(0);
        Scheduler scheduler2 = new SRPTScheduler(0);
        Set<Task> taskSet = Set.of(
                new Task(5, 19, "people"),
                new Task(6,11, "are"),
                new Task(3, 12, "birds")
        );

        for(Task t : taskSet){
            scheduler1.addTask(t);
            scheduler2.addTask(t);
        }

        for(int time = 0; time < 20; time++){
            System.out.printf("scheduler %d tick %-3d: %d \n", 1, time, scheduler1.currentTaskID());
            System.out.printf("scheduler %d tick %-3d: %d \n", 2, time, scheduler2.currentTaskID());
            scheduler1.tick();
            scheduler2.tick();

        }
    }
}

