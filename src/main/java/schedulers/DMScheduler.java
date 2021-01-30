package schedulers;

import java.util.Comparator;

public class DMScheduler extends AbstractScheduler{

    public DMScheduler(int ticks){
        super(Comparator.comparingInt(Task::getRelDeadline), ticks);
    }
}
