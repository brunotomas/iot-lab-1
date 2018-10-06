package view;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.*;
import javax.swing.border.*;

public class Main {
    // temperatura
	JTextField txtTemperatura;
	JTextField minTemperatura;
	JTextField maxTemperatura;
	
	JLabel lblMinTemperatura, lblMaxTemperatura, lblEstadoLedTemperatura;
	
	JPanel pTemperatura, pMinMaxTemperatura, pTemperaturaAtual;
	
	// uumidade
	JTextField txtUmidade;
	JTextField minUmidade;
	JTextField maxUmidade;
	
	JLabel lblMinUmidade, lblMaxUmidade, lblEstadoLedUmidade;
	
	JPanel pUmidade, pMinMaxUmidade, pUmidadeAtual;
	
	JFrame frame;
	JTabbedPane tabby;

	public Main() {
		
		frame = new JFrame("Client");
		tabby = new JTabbedPane();
		
		pTemperatura = new JPanel(new GridLayout(3, 1));
		pUmidade = new JPanel(new GridLayout(3, 1));
		
		iniciaTabTemperatura();
		iniciaTabUmidade();

		tabby.addTab("Temperatura", pTemperatura);
		tabby.addTab("Umidade", pUmidade);

		frame.getContentPane().add(tabby);

		frame.setSize(300, 200);
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setVisible(true);
	}
	
	public void iniciaTabTemperatura() {
		lblMinTemperatura = new JLabel("Min ( °C): ");
		lblMaxTemperatura = new JLabel("Max ( °C): ");

		txtTemperatura = new JTextField();
		txtTemperatura.setEnabled(false);
		
		minTemperatura = new JTextField("10");
		maxTemperatura = new JTextField("30");
		
		pMinMaxTemperatura = new JPanel(new GridLayout(2,2));
		pMinMaxTemperatura.add(lblMinTemperatura);
		pMinMaxTemperatura.add(minTemperatura);
		pMinMaxTemperatura.add(lblMaxTemperatura);
		pMinMaxTemperatura.add(maxTemperatura);
		
		new ThreadTemperatura().start();;
		
		pTemperaturaAtual = new JPanel(new GridLayout(1,2));
		pTemperaturaAtual.add(new JLabel("Temperatura ( °C):"));
		pTemperaturaAtual.add(txtTemperatura);
		
		lblEstadoLedTemperatura = new JLabel("");
		
		pTemperatura.add(pTemperaturaAtual);
		pTemperatura.add(pMinMaxTemperatura);
		pTemperatura.add(lblEstadoLedTemperatura);
	}
	
	public void iniciaTabUmidade() {
		lblMinUmidade = new JLabel("Min ( %): ");
		lblMaxUmidade = new JLabel("Max ( %): ");

		txtUmidade = new JTextField();
		txtUmidade.setEnabled(false);
		
		minUmidade = new JTextField("20");
		maxUmidade = new JTextField("80");
		
		pMinMaxUmidade = new JPanel(new GridLayout(2,2));
		pMinMaxUmidade.add(lblMinUmidade);
		pMinMaxUmidade.add(minUmidade);
		pMinMaxUmidade.add(lblMaxUmidade);
		pMinMaxUmidade.add(maxUmidade);
		
		new ThreadUmidade().start();;
		
		pUmidadeAtual = new JPanel(new GridLayout(1,2));
		pUmidadeAtual.add(new JLabel("Umidade ( %):"));
		pUmidadeAtual.add(txtUmidade);
		
		lblEstadoLedUmidade = new JLabel("");
		pUmidade.add(pUmidadeAtual);
		pUmidade.add(pMinMaxUmidade);
		pUmidade.add(lblEstadoLedUmidade);
	}
	
	public static void main(String[] args)
	{
		new Main();
	}

	public String getTemperatura() {
		String s  = ThreadLocalRandom.current().nextDouble(0, 50) + "";
		return s.substring(0, 5);
	}
	public String getUmidade() {
		String s  = ThreadLocalRandom.current().nextDouble(0, 100) + "";
		return s.substring(0, 5);
	}
	
	class ThreadTemperatura extends Thread {
		public ThreadTemperatura() {
			super();
		}
	 
		public void run() {
			while(true) {
				String t = getTemperatura();
				txtTemperatura.setText(t);
				
				setLedTemperatura();
				
				try {
					sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	class ThreadUmidade extends Thread {
		public ThreadUmidade() {
			super();
		}
	 
		public void run() {
			while(true) {
				String u = getUmidade();
				txtUmidade.setText(u);
				
				setLedUmidade();
				
				try {
					sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void setLedTemperatura() {
		String maxTemp = maxTemperatura.getText().isEmpty()? "0" : maxTemperatura.getText();
		String maxUmi = maxUmidade.getText().isEmpty()? "0" : maxUmidade.getText();
		
		String minTemp = minTemperatura.getText().isEmpty()? "0" : minTemperatura.getText();
		String minUmi = minUmidade.getText().isEmpty()? "0" : minUmidade.getText();
		
		String atualTemp = txtTemperatura.getText().isEmpty()? "0" : txtTemperatura.getText();
		String atualUmi = txtUmidade.getText().isEmpty()? "0" : txtUmidade.getText();
		
		if((toDouble(atualTemp) > toDouble(maxTemp) || toDouble(atualTemp) < toDouble(minTemp)) &&
				toDouble(atualUmi) > toDouble(maxUmi) || toDouble(atualUmi) < toDouble(minUmi)) {
			
			lblEstadoLedTemperatura.setText("Piscando");
		}
		else if(toDouble(atualTemp) > toDouble(maxTemp)) {
			lblEstadoLedTemperatura.setText("Ligado");
		}
		else {
			lblEstadoLedTemperatura.setText("Desligado");
		}
	}
	
	public void setLedUmidade() {
		String maxTemp = maxTemperatura.getText().isEmpty()? "0" : maxTemperatura.getText();
		String maxUmi = maxUmidade.getText().isEmpty()? "0" : maxUmidade.getText();
		
		String minTemp = minTemperatura.getText().isEmpty()? "0" : minTemperatura.getText();
		String minUmi = minUmidade.getText().isEmpty()? "0" : minUmidade.getText();
		
		String atualTemp = txtTemperatura.getText().isEmpty()? "0" : txtTemperatura.getText();
		String atualUmi = txtUmidade.getText().isEmpty()? "0" : txtUmidade.getText();
		
		if((toDouble(atualTemp) > toDouble(maxTemp) || toDouble(atualTemp) < toDouble(minTemp)) &&
				toDouble(atualUmi) > toDouble(maxUmi) || toDouble(atualUmi) < toDouble(minUmi)) {
			
			lblEstadoLedUmidade.setText("Piscando");
		}
		else if(toDouble(atualUmi) > toDouble(maxUmi)) {
			lblEstadoLedUmidade.setText("Ligado");
		}
		else {
			lblEstadoLedUmidade.setText("Desligado");
		}
	}
	
	  public static double toDouble(String d){
	    return Double.parseDouble(d);
	}

}