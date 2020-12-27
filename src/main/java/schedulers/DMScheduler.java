package schedulers;

import java.util.Comparator;

public class DMScheduler extends AbstractScheduler{

    public DMScheduler(){
        super(Comparator.comparingInt(Task::getRelDeadline));
    }
}
