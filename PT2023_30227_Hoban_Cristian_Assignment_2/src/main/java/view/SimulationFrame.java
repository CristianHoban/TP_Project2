package view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
public class SimulationFrame extends JFrame{
    private JLabel clients = new JLabel("Number of clients: ");
    private JLabel queues = new JLabel ("Number of queues: ");
    private JLabel simInterval = new JLabel("Simulation interval: ");
    private JLabel maxminArrTime = new JLabel("Bounds for arrival time: ");
    private JLabel maxminSerTime = new JLabel("Bounds for service time: ");

    private JTextField inputClients = new JTextField(15);
    private JTextField inputQueues = new JTextField(15);
    private JTextField inputSimInt = new JTextField(15);
    private JTextField maxArrTime = new JTextField(15);
    private JTextField minArrTime = new JTextField(15);
    private JTextField maxSerTime = new JTextField(15);
    private JTextField minSerTime = new JTextField(15);

    private JButton ok = new JButton("OK");

   public SimulationFrame() {

       JPanel content = new JPanel();
       content.setLayout(new FlowLayout());

       JPanel leftPanel = new JPanel();
       leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
       leftPanel.add(clients);
       leftPanel.add(Box.createRigidArea(new Dimension(0, 5)));
       leftPanel.add(queues);
       leftPanel.add(Box.createRigidArea(new Dimension(0, 5)));
       leftPanel.add(simInterval);
       leftPanel.add(Box.createRigidArea(new Dimension(0,10)));
       leftPanel.add(maxminArrTime);
       leftPanel.add(Box.createRigidArea(new Dimension(0, 5)));
       leftPanel.add(maxminSerTime);


       JPanel arrivalTime = new JPanel();
       content.setLayout(new FlowLayout());
       arrivalTime.add(minArrTime);
       arrivalTime.add(maxArrTime);

       JPanel serviceTime = new JPanel();
       content.setLayout(new FlowLayout());
       serviceTime.add(minSerTime);
       serviceTime.add(maxSerTime);




       JPanel rightPanel = new JPanel();
       rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
       rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));
       rightPanel.add(inputClients);
       rightPanel.add(inputQueues);
       rightPanel.add(inputSimInt);
       leftPanel.add(Box.createRigidArea(new Dimension(0, 5)));
       rightPanel.add(arrivalTime);
       rightPanel.add(serviceTime);

       content.add(leftPanel);
       content.add(rightPanel);

       JPanel finalContent = new JPanel();
       finalContent.setLayout(new BoxLayout(finalContent, BoxLayout.Y_AXIS));

       finalContent.add((content));
       finalContent.add(ok);
       finalContent.add(Box.createRigidArea(new Dimension(0, 5)));


       this.setTitle("Frame");
       this.setContentPane(finalContent);
       this.pack();

       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   }

   public String getNoClients(){
       return this.inputClients.getText();
   }
    public String getNoQueues(){
        return this.inputQueues.getText();
    }

    public String getTimeLimit(){
       return this.inputSimInt.getText();
    }


    public String getMaxArrTime() {
        return this.maxArrTime.getText();
    }
    public String getMinArrTime() {
        return this.minArrTime.getText();
    }

    public String getMaxSerTime() {
        return this.maxSerTime.getText();
    }
    public String getMinSerTime() {
        return this.minSerTime.getText();
    }
    public void addOKListener(ActionListener actionListener){
       this.ok.addActionListener(actionListener);
    }

    public void showError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

}
