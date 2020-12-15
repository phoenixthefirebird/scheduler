package schedulers;

import java.util.PriorityQueue;

public class EDFScheduler extends AbstractScheduler{

    public EDFScheduler(){
        super((t1, t2) -> {
            int diff;
            try{
                diff = t1.getAbsDeadline() - t2.getAbsDeadline();
            }catch (TaskNotReadyException e){
                diff = 0;
            }
            return diff;
        });
    }

}
