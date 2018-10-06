package view;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.*;
import javax.swing.border.*;

public class Main {

	JTextField txtTemperatura;
	JPanel pTemperatura;
	JFrame frame;
	JTabbedPane tabby;

	public Main() {
		frame = new JFrame("Client");
		tabby = new JTabbedPane();

		txtTemperatura = new JTextField("20 °C");
		new ThreadTemperatura().start();;
		JPanel pTemperatura = new JPanel();
		pTemperatura.add(new JLabel("Temperatura:"));
		pTemperatura.add(txtTemperatura);

		JComponent pUmidade = new JScrollPane(/*image*/);
		tabby.addTab("Temperatura", pTemperatura);
		tabby.addTab("Umidade", pUmidade);

		frame.getContentPane().add(tabby);

		frame.setSize(200, 200);
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setVisible(true);
	}
	public static void main(String[] args)
	{
		Main m = new Main();
/*		JFrame frame = new JFrame("Client");
		JTabbedPane tabby = new JTabbedPane();

		// painel temperatura
		JPanel pTemperatura = new JPanel();
		pTemperatura.add(new JLabel("Temperatura:"));
		pTemperatura.add(txtTemperatura);
		pTemperatura.add(new JLabel("Max Temp:"));
		pTemperatura.add(new JTextField("60 °C"));
		pTemperatura.add(new JLabel("Min Temp:"));
		pTemperatura.add(new JTextField("14 °C"));
		pTemperatura.add(new JRadioButton("OK"));

		// painel umidade
		//String filename = "Piazza di Spagna.jpg";
		//JLabel image = new JLabel( new ImageIcon(filename));
		JComponent pUmidade = new JScrollPane(image);
		tabby.addTab("Temperatura", pTemperatura);
		tabby.addTab("Umidade", pUmidade);

		frame.getContentPane().add(tabby);

		frame.setSize(200, 200);
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setVisible(true);*/
	}

	public String getTemperatura() {
		return ThreadLocalRandom.current().nextDouble(0, 50) + " °C";
	}
	public double getUnidade() {
		return ThreadLocalRandom.current().nextDouble(0, 100);
	}
	
	class ThreadTemperatura extends Thread {
		public ThreadTemperatura() {
			super();
		}
	 
		public void run() {
			while(true) {
				String t = getTemperatura();
				txtTemperatura.setText(t);
				System.out.println(t);
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}