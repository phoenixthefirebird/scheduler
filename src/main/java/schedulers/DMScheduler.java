package schedulers;

public class DMScheduler extends AbstractScheduler{

    public DMScheduler(){
        super((t1, t2) -> t1.getRelDeadline() - t2.getRelDeadline());
    }
}
