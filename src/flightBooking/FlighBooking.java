package flightBooking;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JToggleButton;

public class FlighBooking extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlighBooking frame = new FlighBooking();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FlighBooking() {
		setTitle("Flight Booker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1356, 618);
		
		long currentTimeInMillis = System.currentTimeMillis();
		Date currentDate = new Date(currentTimeInMillis);

		// Get full month name
		Calendar calendarDate = Calendar.getInstance();
		calendarDate.setTime(currentDate);
		int month = calendarDate.get(Calendar.MONTH);
		String[] monthNames = {"January", "February", "March", "April", "May", "June",
		                        "July", "August", "September", "October", "November", "December"};
		String fullMonthName = monthNames[month];

		// Get day of month
		int day = calendarDate.get(Calendar.DAY_OF_MONTH);

		// Combine month name and day
		String formattedDate = fullMonthName + " " + day;
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnPlanlaUc = new JMenu("Planla&Uç");
		menuBar.add(mnPlanlaUc);
		
		JMenu mnYardim = new JMenu("Yardım");
		menuBar.add(mnYardim);
		
		JMenu mnGiris = new JMenu("Giriş Yap");
		menuBar.add(mnGiris);
		
		JMenu mnKayit = new JMenu("Üye Ol");
		mnKayit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("test");
			}
		});
		menuBar.add(mnKayit);
		menuBar.add(mnGiris);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(53, 53, 1232, 460);
		contentPane.add(desktopPane);
		
		// radio buttons
		JRadioButton rdbtnGidisDonus = new JRadioButton("Gidiş-Dönüş");
		rdbtnGidisDonus.setBounds(18, 31, 149, 23);
		
		JRadioButton rdbtnTekYon = new JRadioButton("Tek Yön");
		rdbtnTekYon.setBounds(194, 31, 149, 23);
		
		// action listeners of radio buttons
		rdbtnGidisDonus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtnTekYon.setSelected(false);
				System.out.println("gidis donus");
			}
		});
		rdbtnTekYon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtnGidisDonus.setSelected(false);
				System.out.println("tek yön");
			}
		});
		desktopPane.add(rdbtnTekYon);
		desktopPane.add(rdbtnGidisDonus);
		
		JComboBox<String> comboBoxNereden = new JComboBox<String>();
		comboBoxNereden.setBounds(18, 74, 149, 24);
		comboBoxNereden.addItem("Ankara");
		comboBoxNereden.addItem("Istanbul");
		comboBoxNereden.addItem("Bursa");
		comboBoxNereden.setVisible(true);

		JComboBox<String> comboBoxNereye = new JComboBox<String>();
		comboBoxNereye.setBounds(194, 74, 149, 24);
		comboBoxNereye.addItem("Ankara");
		comboBoxNereye.addItem("Istanbul");
		comboBoxNereye.addItem("Bursa");
		comboBoxNereye.setVisible(true);

		JCalendar calendar = new JCalendar();
		calendar.setVisible(true); 

		desktopPane.add(comboBoxNereden);
		desktopPane.add(comboBoxNereye);
		
		JPanel takvim = new JPanel();
		takvim.setBounds(381, 74, 277, 192);
		takvim.add(calendar);
		takvim.setVisible(false);
		takvim.setLayout(new FlowLayout());
		desktopPane.add(takvim);
		
		JToggleButton tglbtnTamam = new JToggleButton("Tamam");
		takvim.add(tglbtnTamam);
		tglbtnTamam.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				takvim.setVisible(!takvim.isVisible());
			}
		});
		
		
		
		JButton btnTakvim = new JButton("Gidiş: " + formattedDate);
		btnTakvim.setBounds(446, 30, 149, 25);
		
		btnTakvim.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				takvim.setVisible(!takvim.isVisible());
			}
		});
		
		desktopPane.add(btnTakvim);
		
		JPanel yolcular = new JPanel();
		yolcular.setBounds(670, 74, 426, 283);
		yolcular.setVisible(false);
		yolcular.setLayout(null);
	
		JRadioButton rdbtnEkonomi_1 = new JRadioButton("Economy Class");
		rdbtnEkonomi_1.setBounds(80, 5, 129, 23);
		yolcular.add(rdbtnEkonomi_1);
		
		JRadioButton rdbtnBusiness = new JRadioButton("Business Class");
		rdbtnBusiness.setBounds(214, 5, 131, 23);
		yolcular.add(rdbtnBusiness);
		
		// action listeners of radio buttons
		rdbtnEkonomi_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtnBusiness.setSelected(false);
				System.out.println("ekonomi");
			}
		});
		rdbtnBusiness.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtnEkonomi_1.setSelected(false);
				System.out.println("business");
			}
		});
		
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
		
		yolcular.add(passengerSelectionPanel);	
		
		textField = new JTextField(0);
		textField.setBounds(237, 80, 30, 19);
		passengerSelectionPanel.add(textField);
		desktopPane.add(yolcular);

		JButton btnYolcular = new JButton("Yolcular");
		btnYolcular.setBounds(803, 30, 149, 25);
		desktopPane.add(btnYolcular);
		
		btnYolcular.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				yolcular.setVisible(!yolcular.isVisible());
			}
		});
		

		
	



		
	}
}
