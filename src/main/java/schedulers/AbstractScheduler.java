package schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

public abstract class AbstractScheduler implements Scheduler{
    //this is time
    private static int ticks;
    private Task currentTask;
    //this queue orders the elements automatically
    private List<Task> queue;
    private static final int IDLE_TASK_ID = 0;
    private Comparator<Task> comparator;

    public AbstractScheduler(Comparator<Task> comparator, int ticks){
        queue = new ArrayList<>();
        this.comparator = comparator;
        this.ticks = ticks;
        sort();
    }

    public int getTime(){
        return ticks;
    }

    public boolean addTask(Task t){
        try{
            t.getAbsDeadline();
        }catch (TaskNotReadyException e){
            t.release(ticks);
        }
        boolean result = queue.add(t);
        currentTask = queue.get(0); //looking at the highest priority item
        sort();
        return result;
    }

    private void sort(){
        queue = queue.stream().sorted(comparator).collect(Collectors.toList());
    }

    public boolean delTask(Task t){
        boolean result = queue.remove(t);
        currentTask = queue.get(0);
        sort();
        return result;
    }

    public int currentTaskID(){
        if(currentTask == null){
            return IDLE_TASK_ID;
        }
        return currentTask.getTaskID();
    }

    public void tick(){
        currentTask = queue.get(0);
        ++ticks;
        if(currentTask != null) {
            currentTask.oneTick();
            if(currentTask.getRemProcTime() == 0){
                queue.remove(currentTask);
            }
        }
        sort();
        currentTask = queue.get(0);
    }

    public void tick(Task t){
        ++ticks;
        if(t != null) {
            t.oneTick();
            if(t.getRemProcTime() == 0){
                queue.remove(t);
            }
        }
        sort();
    }

    public ArrayList<Task> getList(){
        sort();
        return new ArrayList<>(queue);
    }
}
