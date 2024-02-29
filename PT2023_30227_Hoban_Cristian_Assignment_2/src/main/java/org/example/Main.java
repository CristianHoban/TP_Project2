package org.example;

import controller.Controller;
import view.SimulationFrame;

public class  Main {
    public static void main(String[] args) {
        SimulationFrame frame = new SimulationFrame();
        Controller controller = new Controller(frame);
        frame.setVisible(true);

        /*SimulationManager s = new SimulationManager(15, 9, 3, 1, 3, 3, 10);
        Thread t = new Thread(s);
        t.start();*/ 
    }
}