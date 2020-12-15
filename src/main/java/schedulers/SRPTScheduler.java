package schedulers;

import java.util.PriorityQueue;

//shortest remaining processing time
public class SRPTScheduler extends AbstractScheduler{

    public SRPTScheduler(){
        super((t1, t2) -> t1.getRemProcTime() - t2.getRemProcTime());
    }

}
