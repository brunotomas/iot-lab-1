package cos.ufrj.br.labiot032018.rt.client.view;

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

import cos.ufrj.br.labiot032018.rt.client.utils.LedStateConstants;

import org.eclipse.californium.core.coap.CoAP.Code;
import org.eclipse.californium.core.coap.MediaTypeRegistry;

public class View3 {
	
	// fields for temperature
	JTextField currentTemperatureTextField;
	JTextField minTemperatureTextField;
	JTextField maxTemperatureTextField;
	JLabel minTemperatureLabel, maxTemperatureLabel, ledStateTemperatureLabel;
	JButton okTemperatureButton;
	JPanel mainTemperaturePanel, minMaxTemperaturePanel, currentTemperaturePanel;

	// fields for humidity
	JTextField currentHumidityTextField;
	JTextField minHumidityTextField;
	JTextField maxHumidityTextField;
	JLabel minHumidityLabel, maxHumidityLabel, ledStateHumidityLabel;
	JButton okHumidityButton;
	JPanel mainHumidityPanel, minMaxHumidityPanel, currentHumidityPanel;
	
	// others fields to create the window
	JFrame frame;
	JTabbedPane tabby;

	public View3(String title) {

		frame = new JFrame(title);
		tabby = new JTabbedPane();

		mainTemperaturePanel = new JPanel(new GridLayout(4, 1));
		mainHumidityPanel = new JPanel(new GridLayout(4, 1));

		new ThreadTemperaturaUmidade().start();

		iniciaTabTemperatura();
		iniciaTabUmidade();

		tabby.addTab("Temperatura", mainTemperaturePanel);
		tabby.addTab("Umidade", mainHumidityPanel);

		frame.getContentPane().add(tabby);

		frame.setSize(300, 200);
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setVisible(true);
	}
	
	public JTextField getCurrentTemperatureTextField() {
		return currentTemperatureTextField;
	}

	public void setCurrentTemperatureTextField(JTextField currentTemperatureTextField) {
		this.currentTemperatureTextField = currentTemperatureTextField;
	}

	public JTextField getMinTemperatureTextField() {
		return minTemperatureTextField;
	}

	public void setMinTemperatureTextField(JTextField minTemperatureTextField) {
		this.minTemperatureTextField = minTemperatureTextField;
	}

	public JTextField getMaxTemperatureTextField() {
		return maxTemperatureTextField;
	}

	public void setMaxTemperatureTextField(JTextField maxTemperatureTextField) {
		this.maxTemperatureTextField = maxTemperatureTextField;
	}

	public JLabel getMinTemperatureLabel() {
		return minTemperatureLabel;
	}

	public void setMinTemperatureLabel(JLabel minTemperatureLabel) {
		this.minTemperatureLabel = minTemperatureLabel;
	}

	public JLabel getMaxTemperatureLabel() {
		return maxTemperatureLabel;
	}

	public void setMaxTemperatureLabel(JLabel maxTemperatureLabel) {
		this.maxTemperatureLabel = maxTemperatureLabel;
	}

	public JLabel getLedStateTemperatureLabel() {
		return ledStateTemperatureLabel;
	}

	public void setLedStateTemperatureLabel(JLabel ledStateTemperatureLabel) {
		this.ledStateTemperatureLabel = ledStateTemperatureLabel;
	}

	public JButton getOkTemperatureButton() {
		return okTemperatureButton;
	}

	public void setOkTemperatureButton(JButton okTemperatureButton) {
		this.okTemperatureButton = okTemperatureButton;
	}

	public JPanel getMainTemperaturePanel() {
		return mainTemperaturePanel;
	}

	public void setMainTemperaturePanel(JPanel mainTemperaturePanel) {
		this.mainTemperaturePanel = mainTemperaturePanel;
	}

	public JPanel getMinMaxTemperaturePanel() {
		return minMaxTemperaturePanel;
	}

	public void setMinMaxTemperaturePanel(JPanel minMaxTemperaturePanel) {
		this.minMaxTemperaturePanel = minMaxTemperaturePanel;
	}

	public JPanel getCurrentTemperaturePanel() {
		return currentTemperaturePanel;
	}

	public void setCurrentTemperaturePanel(JPanel currentTemperaturePanel) {
		this.currentTemperaturePanel = currentTemperaturePanel;
	}

	public JTextField getCurrentHumidityTextField() {
		return currentHumidityTextField;
	}

	public void setCurrentHumidityTextField(JTextField currentHumidityTextField) {
		this.currentHumidityTextField = currentHumidityTextField;
	}

	public JTextField getMinHumidityTextField() {
		return minHumidityTextField;
	}

	public void setMinHumidityTextField(JTextField minHumidityTextField) {
		this.minHumidityTextField = minHumidityTextField;
	}

	public JTextField getMaxHumidityTextField() {
		return maxHumidityTextField;
	}

	public void setMaxHumidityTextField(JTextField maxHumidityTextField) {
		this.maxHumidityTextField = maxHumidityTextField;
	}

	public JLabel getMinHumidityLabel() {
		return minHumidityLabel;
	}

	public void setMinHumidityLabel(JLabel minHumidityLabel) {
		this.minHumidityLabel = minHumidityLabel;
	}

	public JLabel getMaxHumidityLabel() {
		return maxHumidityLabel;
	}

	public void setMaxHumidityLabel(JLabel maxHumidityLabel) {
		this.maxHumidityLabel = maxHumidityLabel;
	}

	public JLabel getLedStateHumidityLabel() {
		return ledStateHumidityLabel;
	}

	public void setLedStateHumidityLabel(JLabel ledStateHumidityLabel) {
		this.ledStateHumidityLabel = ledStateHumidityLabel;
	}

	public JButton getOkHumidityButton() {
		return okHumidityButton;
	}

	public void setOkHumidityButton(JButton okHumidityButton) {
		this.okHumidityButton = okHumidityButton;
	}

	public JPanel getMainHumidityPanel() {
		return mainHumidityPanel;
	}

	public void setMainHumidityPanel(JPanel mainHumidityPanel) {
		this.mainHumidityPanel = mainHumidityPanel;
	}

	public JPanel getMinMaxHumidityPanel() {
		return minMaxHumidityPanel;
	}

	public void setMinMaxHumidityPanel(JPanel minMaxHumidityPanel) {
		this.minMaxHumidityPanel = minMaxHumidityPanel;
	}

	public JPanel getCurrentHumidityPanel() {
		return currentHumidityPanel;
	}

	public void setCurrentHumidityPanel(JPanel currentHumidityPanel) {
		this.currentHumidityPanel = currentHumidityPanel;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JTabbedPane getTabby() {
		return tabby;
	}

	public void setTabby(JTabbedPane tabby) {
		this.tabby = tabby;
	}

	public void iniciaTabTemperatura() {
		minTemperatureLabel = new JLabel("Min ( °C): ");
		maxTemperatureLabel = new JLabel("Max ( °C): ");

		currentTemperatureTextField = new JTextField();
		currentTemperatureTextField.setEnabled(false);

		minTemperatureTextField = new JTextField("0");
		maxTemperatureTextField = new JTextField("100");

		minMaxTemperaturePanel = new JPanel(new GridLayout(2,2));
		minMaxTemperaturePanel.add(minTemperatureLabel);
		minMaxTemperaturePanel.add(minTemperatureTextField);
		minMaxTemperaturePanel.add(maxTemperatureLabel);
		minMaxTemperaturePanel.add(maxTemperatureTextField);

		currentTemperaturePanel = new JPanel(new GridLayout(1,2));
		currentTemperaturePanel.add(new JLabel("Temperatura ( °C):"));
		currentTemperaturePanel.add(currentTemperatureTextField);

		ledStateTemperatureLabel = new JLabel("");

		mainTemperaturePanel.add(currentTemperaturePanel);
		mainTemperaturePanel.add(minMaxTemperaturePanel);
		mainTemperaturePanel.add(ledStateTemperatureLabel);

		okTemperatureButton = new JButton("Ok");
		okTemperatureButton.addActionListener(new ButtonTempOkListener());
		mainTemperaturePanel.add(okTemperatureButton);
	}

	public void iniciaTabUmidade() {
		minHumidityLabel = new JLabel("Min ( %): ");
		maxHumidityLabel = new JLabel("Max ( %): ");

		currentHumidityTextField = new JTextField();
		currentHumidityTextField.setEnabled(false);

		minHumidityTextField = new JTextField("0");
		maxHumidityTextField = new JTextField("100");

		minMaxHumidityPanel = new JPanel(new GridLayout(2,2));
		minMaxHumidityPanel.add(minHumidityLabel);
		minMaxHumidityPanel.add(minHumidityTextField);
		minMaxHumidityPanel.add(maxHumidityLabel);
		minMaxHumidityPanel.add(maxHumidityTextField);

		currentHumidityPanel = new JPanel(new GridLayout(1,1));
		currentHumidityPanel.add(new JLabel("Umidade ( %):"));
		currentHumidityPanel.add(currentHumidityTextField);

		ledStateHumidityLabel = new JLabel("");
		mainHumidityPanel.add(currentHumidityPanel);
		mainHumidityPanel.add(minMaxHumidityPanel);
		mainHumidityPanel.add(ledStateHumidityLabel);

		okHumidityButton = new JButton("Ok");
		okHumidityButton.addActionListener(new ButtonUmidadeOkListener());
		mainHumidityPanel.add(okHumidityButton);
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
							currentHumidityTextField.setText(content.split(";")[0]);
							currentTemperatureTextField.setText(content.split(";")[1]);
							
							boolean isBlink = Boolean.parseBoolean(content.split(";")[2]);
							boolean isLedHumidade = Boolean.parseBoolean(content.split(";")[3]);
							boolean isLedTemp = Boolean.parseBoolean(content.split(";")[4]);
							System.out.println(content);
							System.out.println(isBlink);
							System.out.println(isLedHumidade);
							System.out.println(isLedTemp);
							
							if(isBlink) {
								ledStateTemperatureLabel.setText("Piscando");
								ledStateHumidityLabel.setText("Piscando");
							}else {
								/*if(isLedHumidade) {
									ledStateHumidityLabel.setText(LedStateConstants.ON);
								}else {
									ledStateHumidityLabel.setText(LedStateConstants.OFF);
								}*/
								if(isLedTemp) {
									ledStateTemperatureLabel.setText("ON");
								}else {
									ledStateTemperatureLabel.setText("OFF");
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
			String payload = "temperatura;" + minTemperatureTextField.getText() + ";" + maxTemperatureTextField.getText();
			CoapResponse coapRespTempHumidity = clientTempHumidity.post(payload, MediaTypeRegistry.TEXT_PLAIN);

		}
	}

	class ButtonUmidadeOkListener implements ActionListener {
		ButtonUmidadeOkListener() {
		}

		public void actionPerformed(ActionEvent e) {
			CoapClient clientTempHumidity = new CoapClient("coap://10.0.0.103:5683/temp-humidity");
			String payload = "umidade;" + minHumidityTextField.getText() + ";" + maxHumidityTextField.getText();
			CoapResponse coapRespTempHumidity = clientTempHumidity.post(payload, MediaTypeRegistry.TEXT_PLAIN);

		}
	}
}