package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Main {
  public static void main(String[] args)
  {
    JFrame frame = new JFrame("Client");
    JTabbedPane tabby = new JTabbedPane();

    // painel temperatura
    JPanel pTemperatura = new JPanel();
    pTemperatura.add(new JLabel("Temperatura:"));
    pTemperatura.add(new JTextField("28 °C"));
    pTemperatura.add(new JLabel("Max Temp:"));
    pTemperatura.add(new JTextField("60 °C"));
    pTemperatura.add(new JLabel("Min Temp:"));
    pTemperatura.add(new JTextField("14 °C"));
    pTemperatura.add(new JRadioButton("LED"));
    pTemperatura.add(new JRadioButton("Auto"));

    // painel umidade
    //String filename = "Piazza di Spagna.jpg";
    //JLabel image = new JLabel( new ImageIcon(filename));
    JComponent pUmidade = new JScrollPane(/*image*/);
    tabby.addTab("Temperatura", pTemperatura);
    tabby.addTab("Umidade", pUmidade);

    frame.getContentPane().add(tabby);

    frame.setSize(200, 200);
    frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    frame.setVisible(true);
  }
}