package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainFrame extends JFrame{

    private JScrollPane scrollPaneLeft;
    private JScrollPane scrollPaneRight;

    private JTextArea right;

    private JPanel titles;
    private JLabel clients;
    private JLabel queues;
    private JTextArea left;
    public MainFrame() {
        right = new JTextArea("");
        left = new JTextArea("");
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(600,700));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));;

        JPanel scrollPanel = new JPanel();
        scrollPanel.setPreferredSize(new Dimension(600, 600));
        scrollPanel.setLayout(new FlowLayout());

        scrollPaneLeft = new JScrollPane();
        scrollPaneRight = new JScrollPane();
        scrollPaneLeft.setPreferredSize(new Dimension(280, 590));
        scrollPaneRight.setPreferredSize(new Dimension(280, 590));
        scrollPaneRight.setViewportView(right);
        scrollPaneLeft.setViewportView(left);
        scrollPanel.add(scrollPaneLeft);
        scrollPanel.add(scrollPaneRight);

        titles = new JPanel();
        titles.setLayout(new FlowLayout());;
        titles.setPreferredSize(new Dimension(600, 100));
        clients = new JLabel("CLIENTS:");
        clients.setPreferredSize(new Dimension(250,100));
        queues = new JLabel("QUEUES IN REAL TIME:");
        queues.setPreferredSize(new Dimension(250,100));
        titles.add(clients);
        titles.add(queues);

        mainPanel.add(titles);
        mainPanel.add(scrollPanel);


        this.setTitle("Simulation");
        this.setContentPane(mainPanel);
        this.pack();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void setLeft(String s){
        left.setText(s);
    }
    public void setRight(String s){
        right.setText(s);
    }

    public void displayResults(String s) {
        JOptionPane.showMessageDialog(this, s);
    }

}
