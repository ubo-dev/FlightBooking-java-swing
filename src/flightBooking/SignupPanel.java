package flightBooking;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SignupPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public SignupPanel() {
		
		JPanel signUp = new JPanel();
		signUp.setBounds(670, 74, 426, 283);
		signUp.setVisible(false);
		signUp.setLayout(new FlowLayout());
		
		JPanel passengerSelectionPanel = new JPanel();
		passengerSelectionPanel.setBounds(0, 33, 432, 250);
		passengerSelectionPanel.setBorder(BorderFactory.createTitledBorder("Kabin ve yolcu seçimi (Cabin and Passenger Selection)"));
		
		JLabel adultLabel = new JLabel("Yetişkin (Adult):");
		adultLabel.setBounds(70, 38, 123, 19);
		JTextField adultTextField = new JTextField(0); // Set a preferred width for the text field
		adultTextField.setBounds(237, 38, 30, 19);

		JLabel childLabel = new JLabel("Çocuk (Child 12-11):");
		childLabel.setBounds(68, 82, 137, 15);

		JLabel babyLabel = new JLabel("Bebek (Baby 0-2):");
		babyLabel.setBounds(70, 130, 123, 15);
		JTextField babyTextField = new JTextField(0);
		babyTextField.setBounds(237, 128, 30, 19);
		passengerSelectionPanel.setLayout(null);

		// Add labels and text fields to the passenger selection panel
		passengerSelectionPanel.add(adultLabel);
		passengerSelectionPanel.add(adultTextField);
		passengerSelectionPanel.add(childLabel);
		passengerSelectionPanel.add(babyLabel);
		passengerSelectionPanel.add(babyTextField);
		
		signUp.add(passengerSelectionPanel);	
	}

}
