

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SecretMessagesGUI extends JFrame {
	private JTextField txtKey;
	private JTextArea txtIn;
	private JTextArea txtOut;
	public SecretMessagesGUI() {
		setTitle("Reno's Secret Messages App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		txtIn = new JTextArea();
		txtIn.setBounds(12, 10, 560, 140);
		getContentPane().add(txtIn);
		
		txtOut = new JTextArea();
		txtOut.setBounds(12, 211, 560, 140);
		getContentPane().add(txtOut);
		
		txtKey = new JTextField();
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
				try {
					String message = txtIn.getText();
					int keyVal = Integer.parseInt(txtKey.getText());
					txtOut.setText(encode(message, keyVal));
				}
				catch (Exception ex) {
					
				}
			}
		});
		btnNewButton.setBounds(319, 170, 121, 21);
		getContentPane().add(btnNewButton);
	}
	public String encode(String message, int keyVal) {
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

	public static void main(String[] args) {
		SecretMessagesGUI theApp = new SecretMessagesGUI();
		theApp.setSize(new java.awt.Dimension(600, 400));
		theApp.setVisible(true);
	}
}
