package view;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.*;
import javax.swing.border.*;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.core.coap.CoAP.Code;
import org.eclipse.californium.core.coap.MediaTypeRegistry;

public class Main {
	// temperatura
	JTextField txtTemperatura;
	JTextField minTemperatura;
	JTextField maxTemperatura;
	JButton btnTempOk;

	JLabel lblMinTemperatura, lblMaxTemperatura, lblEstadoLedTemperatura;

	JPanel pTemperatura, pMinMaxTemperatura, pTemperaturaAtual;

	// uumidade
	JTextField txtUmidade;
	JTextField minUmidade;
	JTextField maxUmidade;
	JButton btnUmidadeOk;

	JLabel lblMinUmidade, lblMaxUmidade, lblEstadoLedUmidade;

	JPanel pUmidade, pMinMaxUmidade, pUmidadeAtual;

	JFrame frame;
	JTabbedPane tabby;

	public Main() {

		frame = new JFrame("Client");
		tabby = new JTabbedPane();

		pTemperatura = new JPanel(new GridLayout(4, 1));
		pUmidade = new JPanel(new GridLayout(4, 1));

		new ThreadTemperaturaUmidade().start();;

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

		minTemperatura = new JTextField("0");
		maxTemperatura = new JTextField("100");

		pMinMaxTemperatura = new JPanel(new GridLayout(2,2));
		pMinMaxTemperatura.add(lblMinTemperatura);
		pMinMaxTemperatura.add(minTemperatura);
		pMinMaxTemperatura.add(lblMaxTemperatura);
		pMinMaxTemperatura.add(maxTemperatura);

		pTemperaturaAtual = new JPanel(new GridLayout(1,2));
		pTemperaturaAtual.add(new JLabel("Temperatura ( °C):"));
		pTemperaturaAtual.add(txtTemperatura);

		lblEstadoLedTemperatura = new JLabel("");

		pTemperatura.add(pTemperaturaAtual);
		pTemperatura.add(pMinMaxTemperatura);
		pTemperatura.add(lblEstadoLedTemperatura);

		btnTempOk = new JButton("Ok");
		btnTempOk.addActionListener(new ButtonTempOkListener());
		pTemperatura.add(btnTempOk);
	}

	public void iniciaTabUmidade() {
		lblMinUmidade = new JLabel("Min ( %): ");
		lblMaxUmidade = new JLabel("Max ( %): ");

		txtUmidade = new JTextField();
		txtUmidade.setEnabled(false);

		minUmidade = new JTextField("0");
		maxUmidade = new JTextField("100");

		pMinMaxUmidade = new JPanel(new GridLayout(2,2));
		pMinMaxUmidade.add(lblMinUmidade);
		pMinMaxUmidade.add(minUmidade);
		pMinMaxUmidade.add(lblMaxUmidade);
		pMinMaxUmidade.add(maxUmidade);

		pUmidadeAtual = new JPanel(new GridLayout(1,1));
		pUmidadeAtual.add(new JLabel("Umidade ( %):"));
		pUmidadeAtual.add(txtUmidade);

		lblEstadoLedUmidade = new JLabel("");
		pUmidade.add(pUmidadeAtual);
		pUmidade.add(pMinMaxUmidade);
		pUmidade.add(lblEstadoLedUmidade);

		btnUmidadeOk = new JButton("Ok");
		btnUmidadeOk.addActionListener(new ButtonUmidadeOkListener());
		pUmidade.add(btnUmidadeOk);
	}

	public static void main(String[] args)
	{
		new Main();
	}

	class ThreadTemperaturaUmidade extends Thread {
		public ThreadTemperaturaUmidade() {
			super();
		}

		public void run() {
			CoapClient clientTempHumidity = new CoapClient("coap://10.0.0.103:5683/temp-humidity");
			CoapObserveRelation cor = clientTempHumidity.observe(
					new CoapHandler() {
						@Override public void onLoad(CoapResponse response) {
							String content = response.getResponseText();
							txtUmidade.setText(content.split(";")[0]);
							txtTemperatura.setText(content.split(";")[1]);
							
							boolean isBlink = Boolean.parseBoolean(content.split(";")[2]);
							boolean isLedHumidade = Boolean.parseBoolean(content.split(";")[3]);
							boolean isLedTemp = Boolean.parseBoolean(content.split(";")[4]);
							System.out.println(content);
							System.out.println(isBlink);
							System.out.println(isLedHumidade);
							System.out.println(isLedTemp);
							
							if(isBlink) {
								lblEstadoLedTemperatura.setText("Piscando");
								lblEstadoLedUmidade.setText("Piscando");
							}else {
								if(isLedHumidade) {
									lblEstadoLedUmidade.setText("ON");
								}else {
									lblEstadoLedUmidade.setText("OFF");
								}
								if(isLedTemp) {
									lblEstadoLedTemperatura.setText("ON");
								}else {
									lblEstadoLedTemperatura.setText("OFF");
								}
							}
							
						}

						@Override public void onError() {
							System.err.println("OBSERVING FAILED (press enter to exit)");
						}
					});
		}

	}

	class ButtonTempOkListener implements ActionListener {
		ButtonTempOkListener() {
		}

		public void actionPerformed(ActionEvent e) {
			CoapClient clientTempHumidity = new CoapClient("coap://10.0.0.103:5683/temp-humidity");
			String payload = "temperatura;" + minTemperatura.getText() + ";" + maxTemperatura.getText();
			CoapResponse coapRespTempHumidity = clientTempHumidity.post(payload, MediaTypeRegistry.TEXT_PLAIN);

		}
	}

	class ButtonUmidadeOkListener implements ActionListener {
		ButtonUmidadeOkListener() {
		}

		public void actionPerformed(ActionEvent e) {
			CoapClient clientTempHumidity = new CoapClient("coap://10.0.0.103:5683/temp-humidity");
			String payload = "umidade;" + minUmidade.getText() + ";" + maxUmidade.getText();
			CoapResponse coapRespTempHumidity = clientTempHumidity.post(payload, MediaTypeRegistry.TEXT_PLAIN);

		}

	}
}