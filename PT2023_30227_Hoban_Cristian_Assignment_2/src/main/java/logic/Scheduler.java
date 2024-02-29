package logic;

import model.Server;
import model.Task;

import java.util.ArrayList;
import java.util.List;



public class Scheduler {
    private List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerServer;

    public Scheduler(int maxNoServers) {
        servers = new ArrayList<>();
        for (int i = 0; i < maxNoServers; i++) {
            Server s = new Server();
            Thread t = new Thread(s);
            t.start();
            this.servers.add(s);

        }
    }

    public int findMinWaitingPeriod(){
        int min = 9999999;
        for(Server s : this.servers){
            int aux = s.getWaitingPeriod().intValue();
            if(aux < min)
                min = aux;
        }
        return min;
    }
    public List<Server> getServers(){
        return servers;
    }

    public Server dispatchTask(Task t){
        int i = this.findMinWaitingPeriod();
        for(Server s : this.servers){
            int aux = s.getWaitingPeriod().intValue();
            if(aux == i){
                s.addTask(t);
                return s;
                //break;
            }

        }
        return null;
    }

    public int noOfClientsInQueues(){
        int i = 0;
        for(Server s : this.servers){
            i += s.getTasks().size();
        }
        return i;
    }

    public String toString() {
        String result = "";
        int i = 1;
        for (Server s : servers) {
            result += "Queue " + i + ":\n" + s.toString() + "\n";
            i++;
        }
        return result;
    }
}
