package model;

import model.Task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;

    public Server(){
       tasks = new LinkedBlockingQueue<Task>();
       waitingPeriod = new AtomicInteger(0);
    }

    public void addTask(Task newTask){
        tasks.add(newTask);
        waitingPeriod.addAndGet(newTask.getServiceTime());

    }

    public void setTasks(BlockingQueue<Task> tasks) {
        this.tasks = tasks;
    }



    public BlockingQueue<Task> getTasks() {
        return tasks;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    @Override
    public void run() {
        while(true){
            if(!tasks.isEmpty()) {
                try {
                   // Thread.sleep((tasks.peek().getServiceTime())*1000);
                    int i = tasks.peek().getServiceTime();
                    for(int j = 0; j < i-1 ;j++){
                        tasks.peek().setServiceTime(tasks.peek().getServiceTime()-1);
                        Thread.sleep(1000);
                    }

                    waitingPeriod.addAndGet(-tasks.peek().getServiceTime());
                    tasks.remove(tasks.peek());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public String toString(){
        String result = "";
        for(Task t : this.tasks){
            result += t.toString() + "\n";
        }
        return result;
    }
}
