package schedulers;


import java.util.Comparator;

//shortest remaining processing time
public class SRPTScheduler extends AbstractScheduler{

    public SRPTScheduler(int ticks){
        super(Comparator.comparingInt(Task::getRemProcTime), ticks);
    }

}
