package controller;

import view.MainFrame;
import view.SimulationFrame;
import logic.SimulationManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private SimulationFrame simulationFrame;
    MainFrame mainFrame = new MainFrame();

    public Controller(SimulationFrame simulationFrame){
        this.simulationFrame = simulationFrame;
        this.simulationFrame.addOKListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int noClients;
                int noQueues;
                int timeLimit;
                int minA;
                int maxA;
                int minS;
                int maxS;

                try{
                    noClients = Integer.parseInt(simulationFrame.getNoClients());
                    noQueues = Integer.parseInt(simulationFrame.getNoQueues());
                    timeLimit = Integer.parseInt(simulationFrame.getTimeLimit());
                    minA = Integer.parseInt(simulationFrame.getMinArrTime());
                    maxA = Integer.parseInt(simulationFrame.getMaxArrTime());
                    minS = Integer.parseInt(simulationFrame.getMinSerTime());
                    maxS = Integer.parseInt(simulationFrame.getMaxSerTime());

                    SimulationManager simulationManager = new SimulationManager(timeLimit, maxA, minA, minS, maxS, noQueues, noClients);
                    simulationManager.getMainFrame().setVisible(true);
                    Thread t = new Thread(simulationManager);
                    t.start();
                    simulationFrame.dispose();

                }
                catch(NumberFormatException ex){
                    simulationFrame.showError("Incorrect input data!");
                }
            }
        });

    }
}
