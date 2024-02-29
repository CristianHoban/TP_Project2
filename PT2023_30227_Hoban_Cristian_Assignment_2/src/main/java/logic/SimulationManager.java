package logic;

import logic.Scheduler;
import model.Server;
import model.Task;
import view.MainFrame;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.Random;



public class SimulationManager implements Runnable{
    public int timeLimit;
    public int maxArrivalTime;
    public int minArrivalTime;

    public int minServiceTime;
    public int maxServiceTime;
    public int numberOfServers;
    public int numberOfClients;

    private Scheduler scheduler;
    private MainFrame mainFrame;
    private List<Task> generatedTasks;


    public Scheduler getScheduler() {
        return scheduler;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public List<Task> getGeneratedTasks() {
        return generatedTasks;
    }

    public SimulationManager(int timeLimit, int maxArrivalTime, int minArrivalTime, int minServiceTime, int maxServiceTime, int numberOfServers, int numberOfClients) {
        this.timeLimit = timeLimit;
        this.maxArrivalTime = maxArrivalTime;
        this.minArrivalTime = minArrivalTime;
        this.minServiceTime = minServiceTime;
        this.maxServiceTime = maxServiceTime;
        this.numberOfServers = numberOfServers;
        this.numberOfClients = numberOfClients;

        scheduler = new Scheduler(numberOfServers);

        this.generateNRandomTasks();

        mainFrame = new MainFrame();
    }


    public void generateNRandomTasks(){
        generatedTasks = new ArrayList<>();
        Random random = new Random();
        int arrivalBound = maxArrivalTime - minArrivalTime + 1;
        int serviceBound = maxServiceTime - minServiceTime + 1;
        for(int i = 0;i < numberOfClients;i++){
            int service = random.nextInt(serviceBound) + minServiceTime;
            int arrival = random.nextInt(arrivalBound) + minArrivalTime;
            Task t = new Task(i+1, arrival, service);
            generatedTasks.add(t);
        }
    }

    public String printAllClients(int time){
        String result = "";
        for(Task t : generatedTasks){
            if(t.getArrivalTime() > time) {
                result += t.toString() + "\n";
            }
        }
        return result;
    }

    @Override
    public void run() {
        double averageServiceTime = 0;
        double averageWaitingTime = 0;
        int noOfClients = 0;
        int currentTime = 0;
        int maxNoClients = -1;
        int maxTime = 0;
        try {
            FileWriter file = new FileWriter("log.txt");
        while(currentTime <= timeLimit){
            for(Task e:generatedTasks){
                if(e.getArrivalTime() == currentTime){
                    Server s = scheduler.dispatchTask(e);
                    noOfClients++;
                    if(maxNoClients < scheduler.noOfClientsInQueues())
                        maxTime = currentTime;
                    averageServiceTime += e.getServiceTime();
                    averageWaitingTime += s.getWaitingPeriod().doubleValue();

                }
            }
            mainFrame.setLeft(printAllClients(currentTime));
            String res = "Time " + currentTime + ":\n" + scheduler.toString();
            file.write(res);
            mainFrame.setRight(res);
            /*System.out.println("Time " + currentTime + ":\n");
            System.out.println(scheduler.toString());*/
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            currentTime++;
        }
        String output = "Average service time: " + averageServiceTime/noOfClients + "\n" +
                "Average waiting time: " + averageWaitingTime/noOfClients + "\n" +
                "Peak hour: " + maxTime;
            mainFrame.displayResults(output);
        file.write(output);
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
