package schedulers;

import java.util.Comparator;
import java.util.TreeSet;

public abstract class AbstractScheduler implements Scheduler{
    //this is time
    private static int ticks;
    private static Task currentTask;
    //this queue orders the elements automatically
    private static TreeSet<Task> queue;
    private static final int IDLE_TASK_ID = 0;

    public AbstractScheduler(Comparator<Task> comparator){
        queue = new TreeSet<>(comparator);
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
        currentTask = queue.first(); //looking at the highest priority item
        return result;
    }

    public boolean delTask(Task t){
        boolean result = queue.remove(t);
        currentTask = queue.first();
        return result;
    }

    public int currentTaskID(){
        if(currentTask == null){
            return IDLE_TASK_ID;
        }
        return currentTask.getTaskID();
    }

    public void tick(){
        currentTask = queue.first();
        ++ticks;
        if(currentTask != null) {
            currentTask.oneTick();
            if(currentTask.getRemProcTime() == 0){
                queue.remove(currentTask);
            }
        }
        currentTask = queue.first();
    }
}
