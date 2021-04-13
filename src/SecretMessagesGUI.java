

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class SecretMessagesGUI extends JFrame {
	private JTextField txtKey;
	private JTextArea txtIn;
	private JTextArea txtOut;
	private JSlider slider;
	public SecretMessagesGUI() {
		getContentPane().setBackground(new Color(192, 192, 192));
		setTitle("Reno's Secret Messages App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		txtIn = new JTextArea();
		txtIn.setWrapStyleWord(true);
		txtIn.setLineWrap(true);
		txtIn.setForeground(new Color(0, 255, 0));
		txtIn.setBackground(new Color(0, 0, 0));
		txtIn.setFont(new Font("Consolas", Font.BOLD, 18));
		txtIn.setBounds(12, 10, 560, 140);
		getContentPane().add(txtIn);
		
		txtOut = new JTextArea();
		txtOut.setWrapStyleWord(true);
		txtOut.setLineWrap(true);
		txtOut.setForeground(new Color(0, 255, 0));
		txtOut.setBackground(new Color(0, 0, 0));
		txtOut.setFont(new Font("Consolas", Font.BOLD, 18));
		txtOut.setBounds(12, 211, 560, 140);
		getContentPane().add(txtOut);
		
		txtKey = new JTextField();
		txtKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSlider();
			}
		});
		txtKey.setBounds(269, 171, 38, 19);
		getContentPane().add(txtKey);
		txtKey.setColumns(10);
		
		JLabel lblKey = new JLabel("Key:");
		lblKey.setFont(new Font("Monospaced", Font.PLAIN, 12));
		lblKey.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKey.setBounds(207, 174, 50, 13);
		getContentPane().add(lblKey);
		
		JButton btnNewButton = new JButton("Encode / Decode");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSlider();
			}
		});
		btnNewButton.setBounds(319, 170, 151, 21);
		getContentPane().add(btnNewButton);
		
		slider = new JSlider();
		slider.setFont(new Font("MS UI Gothic", Font.PLAIN, 10));
		slider.setPaintLabels(true);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//Default encoding proceddure happens here.
				int keyVal = slider.getValue();
				String message = txtIn.getText();
				txtKey.setText("" + keyVal);
				txtOut.setText(caesar(message, keyVal));
			}
		});
		slider.setMajorTickSpacing(13);
		slider.setValue(3);
		slider.setMinimum(-26);
		slider.setMaximum(26);
		slider.setMinorTickSpacing(1);
		slider.setSnapToTicks(true);
		slider.setBackground(new Color(192, 192, 192));
		slider.setBounds(12, 160, 200, 41);
		getContentPane().add(slider);
	}
	public String caesar(String message, int keyVal) {
		String output = "";
		
		char key = (char)keyVal;
		
		for (int i = 0; i < message.length(); i++) {
			
			char input = message.charAt(i);
			
			if (input >= 'A' && input <= 'Z') {
				input += key;
				
				if (input > 'Z')
					input -= 26;
				if (input < 'A')
					input += 26;
			}
			else if (input >= 'a' && input <= 'z') {
				input += key;
				
				if (input > 'z')
					input -= 26;
				if (input < 'a')
					input += 26;
			}
			else if (input >= '0' && input <= '9') {
				input += (keyVal % 10);
				
				if (input > '9')
					input -= 10;
				if (input < '0')
					input += 10;
			}
			output += input;
		}
		
		return output;		
	}
	//Set slider with the value from the key field. If the values are the same, it peforms the encoding.
	private void setSlider() {
		try {
			int keyVal = Integer.parseInt(txtKey.getText());
			if (keyVal == slider.getValue()) {
				String message = txtIn.getText();
				txtKey.setText("" + keyVal);
				txtOut.setText(caesar(message, keyVal));
			}
			else {
				slider.setValue(keyVal);
				txtKey.selectAll();
			}
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Please enter a whole number for the encryption key.");
			txtKey.requestFocus();
			txtKey.selectAll();
		}
	}

	public static void main(String[] args) {
		SecretMessagesGUI theApp = new SecretMessagesGUI();
		theApp.setSize(new java.awt.Dimension(600, 400));
		theApp.setVisible(true);
	}
}
