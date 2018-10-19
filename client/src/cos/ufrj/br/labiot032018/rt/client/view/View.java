package cos.ufrj.br.labiot032018.rt.client.view;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.border.*;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.core.coap.CoAP.Code;
import org.eclipse.californium.core.coap.MediaTypeRegistry;

public class View {

	// fields for temperature
	private JTextField currentTemperatureTextField;
	private JTextField minTemperatureTextField;
	private JTextField maxTemperatureTextField;
	private JLabel minTemperatureLabel, maxTemperatureLabel, ledStateTemperatureLabel;
	private JButton okTemperatureButton;
	private JPanel mainTemperaturePanel, minMaxTemperaturePanel, currentTemperaturePanel;

	// fields for humidity
	private JTextField currentHumidityTextField;
	private JTextField minHumidityTextField;
	private JTextField maxHumidityTextField;
	private JLabel minHumidityLabel, maxHumidityLabel, ledStateHumidityLabel;
	private JButton okHumidityButton;
	private JPanel mainHumidityPanel, minMaxHumidityPanel, currentHumidityPanel;

	private String serverIp;

	// others fields to create the window
	private JFrame frame;
	private JTabbedPane tabby;

	public View(String title) {

		frame = new JFrame(title);
		tabby = new JTabbedPane();

		serverIp = initDialog();

		mainTemperaturePanel = new JPanel(new GridLayout(4, 1));
		mainHumidityPanel = new JPanel(new GridLayout(4, 1));

		initTabbyTemperature();
		initTabbyHumidity();

		tabby.addTab("Temperature", mainTemperaturePanel);
		tabby.addTab("Humidity", mainHumidityPanel);

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
	
	public String getServerIp() {
		return serverIp;
	}
	
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public void initTabbyTemperature() {
		minMaxTemperaturePanel = new JPanel(new GridLayout(2,2));
		minTemperatureLabel = new JLabel("Min ( °C): ");
		maxTemperatureLabel = new JLabel("Max ( °C): ");
		minTemperatureTextField = new JTextField(0);
		maxTemperatureTextField = new JTextField(100);
		minMaxTemperaturePanel.add(minTemperatureLabel);
		minMaxTemperaturePanel.add(minTemperatureTextField);
		minMaxTemperaturePanel.add(maxTemperatureLabel);
		minMaxTemperaturePanel.add(maxTemperatureTextField);

		currentTemperaturePanel = new JPanel(new GridLayout(1,2));
		currentTemperatureTextField = new JTextField();
		currentTemperatureTextField.setEnabled(false);
		currentTemperaturePanel.add(new JLabel("Temperature ( °C):"));
		currentTemperaturePanel.add(currentTemperatureTextField);

		ledStateTemperatureLabel = new JLabel("");
		okTemperatureButton = new JButton("Ok");
		mainTemperaturePanel.add(currentTemperaturePanel);
		mainTemperaturePanel.add(minMaxTemperaturePanel);
		mainTemperaturePanel.add(ledStateTemperatureLabel);
		mainTemperaturePanel.add(okTemperatureButton);
	}

	public void initTabbyHumidity() {
		minMaxHumidityPanel = new JPanel(new GridLayout(2,2));
		minHumidityLabel = new JLabel("Min ( %): ");
		maxHumidityLabel = new JLabel("Max ( %): ");
		minHumidityTextField = new JTextField(0);
		maxHumidityTextField = new JTextField(100);
		minMaxHumidityPanel.add(minHumidityLabel);
		minMaxHumidityPanel.add(minHumidityTextField);
		minMaxHumidityPanel.add(maxHumidityLabel);
		minMaxHumidityPanel.add(maxHumidityTextField);

		currentHumidityPanel = new JPanel(new GridLayout(1,1));
		currentHumidityTextField = new JTextField();
		currentHumidityTextField.setEnabled(false);
		currentHumidityPanel.add(new JLabel("humidity ( %):"));
		currentHumidityPanel.add(currentHumidityTextField);

		ledStateHumidityLabel = new JLabel("");
		okHumidityButton = new JButton("Ok");
		mainHumidityPanel.add(currentHumidityPanel);
		mainHumidityPanel.add(minMaxHumidityPanel);
		mainHumidityPanel.add(ledStateHumidityLabel);
		mainHumidityPanel.add(okHumidityButton);
	}

	public String initDialog(){
		String[] options = {"OK"};
		JPanel panel = new JPanel();
		JLabel label = new JLabel("IP");
		JTextField serverIpTextField = new JTextField(10);
		panel.add(label);
		panel.add(serverIpTextField);

		JOptionPane.showOptionDialog(null, panel, "Server IP", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options , options[0]);

		while(!isValidIPV4(serverIpTextField.getText()))
		{
			JOptionPane.showOptionDialog(null, panel, "Server IP", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options , options[0]);	
		}
		
		System.out.println(isValidIPV4(serverIpTextField.getText()));

		return serverIpTextField.getText();
	}

	public static boolean isValidIPV4(final String ip)
	{          
        Pattern ptn = Pattern.compile("^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$");
        Matcher mtch = ptn.matcher(ip);
        return mtch.find();
	}
}