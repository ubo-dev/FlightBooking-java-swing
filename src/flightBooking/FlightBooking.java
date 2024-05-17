package flightBooking;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import flightBooking.dto.RegisterRequest;
import flightBooking.repository.flight.FlightRepository;
import flightBooking.repository.flight.FlightRepositoryImpl;
import flightBooking.repository.passenger.PassengerRepository;
import flightBooking.repository.passenger.PassengerRepositoryImpl;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

public class FlightBooking extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField emailField;
	private JTextField passwordField;
	private JTextField phonenNumber;
	private JTextField tcNumber;
	private final PassengerRepository passengerRepository;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlightBooking frame = new FlightBooking(new PassengerRepositoryImpl(), new FlightRepositoryImpl());
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
	public FlightBooking(PassengerRepository passengerRepository, FlightRepository flightRepository) {
		this.passengerRepository = passengerRepository;
		setTitle("Flight Booker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1356, 814);

		long currentTimeInMillis = System.currentTimeMillis();
		Date currentDate = new Date(currentTimeInMillis);

		// Get full month name
		Calendar calendarDate = Calendar.getInstance();
		calendarDate.setTime(currentDate);
		int month = calendarDate.get(Calendar.MONTH);
		String[] monthNames = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		String fullMonthName = monthNames[month];

		// Get day of month
		int day = calendarDate.get(Calendar.DAY_OF_MONTH);

		// Combine month name and day
		String formattedDate = fullMonthName + " " + day;

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1344, 777);
		contentPane.add(tabbedPane);

		JDesktopPane uyeOl = new JDesktopPane();
		tabbedPane.addTab("Üye Ol", null, uyeOl, null);
		uyeOl.setLayout(null);

		firstName = new JTextField();
		firstName.setBounds(553, 112, 186, 19);
		uyeOl.add(firstName);
		firstName.setColumns(10);

		lastName = new JTextField();
		lastName.setColumns(10);
		lastName.setBounds(553, 143, 186, 19);
		uyeOl.add(lastName);

		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(553, 177, 186, 19);
		uyeOl.add(emailField);

		passwordField = new JTextField();
		passwordField.setColumns(10);
		passwordField.setBounds(553, 208, 186, 19);
		uyeOl.add(passwordField);

		phonenNumber = new JTextField();
		phonenNumber.setColumns(10);
		phonenNumber.setBounds(553, 240, 186, 19);
		uyeOl.add(phonenNumber);

		tcNumber = new JTextField();
		tcNumber.setColumns(10);
		tcNumber.setBounds(553, 271, 186, 19);
		uyeOl.add(tcNumber);

		JLabel lblIsim = new JLabel("İsim");
		lblIsim.setBounds(465, 114, 70, 15);
		uyeOl.add(lblIsim);

		JLabel lblSoyIsim = new JLabel("Soy İsim");
		lblSoyIsim.setBounds(465, 145, 70, 15);
		uyeOl.add(lblSoyIsim);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(465, 179, 70, 15);
		uyeOl.add(lblEmail);

		JLabel lblifre = new JLabel("Şifre");
		lblifre.setBounds(465, 210, 70, 15);
		uyeOl.add(lblifre);

		JLabel lblTelefonNumaras = new JLabel("Telefon Numarası");
		lblTelefonNumaras.setBounds(398, 242, 137, 15);
		uyeOl.add(lblTelefonNumaras);

		JLabel lblTcNo = new JLabel("TC No(11 karakter)");
		lblTcNo.setBounds(398, 273, 137, 15);
		uyeOl.add(lblTcNo);

		JLabel lblyeOl = new JLabel("Üye Ol");
		lblyeOl.setBounds(588, 50, 70, 15);
		uyeOl.add(lblyeOl);

		JButton btnyeOl = new JButton("Üye Ol");
		btnyeOl.setBounds(588, 361, 117, 25);
		uyeOl.add(btnyeOl);

		JDesktopPane desktopPane = new JDesktopPane();
		tabbedPane.addTab("Home", null, desktopPane, null);

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
		yolcular.setBounds(670, 74, 426, 197);
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
		passengerSelectionPanel.setBounds(0, 33, 432, 166);
		passengerSelectionPanel
				.setBorder(BorderFactory.createTitledBorder("Kabin ve yolcu seçimi (Cabin and Passenger Selection)"));

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

		JButton btnUuAra = new JButton("Uçuş Ara");
		btnUuAra.setBounds(1119, 120, 164, 71);
		desktopPane.add(btnUuAra);
		btnUuAra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Uçuş ara");
				String type = "";
				if (rdbtnBusiness.isSelected())
					type = "business";
				else if (rdbtnEkonomi_1.isSelected())
					type = "economy";

				String departureCity = comboBoxNereden.getSelectedItem().toString();
				String destinationCity = comboBoxNereye.getSelectedItem().toString();
				Date date = calendar.getDate();
				System.out.println("type: " + type + " departureCity: " + departureCity + " destinationCity: "
						+ destinationCity + " flightDate: " + date);
				flightRepository.searchFlights(null);
			}
		});

		table = new JTable();
		table.setBounds(74, 329, 1198, 387);
		desktopPane.add(table);
		String[] columnNames = { "" };

		btnYolcular.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				yolcular.setVisible(!yolcular.isVisible());
			}
		});
		btnyeOl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegisterRequest request = new RegisterRequest(firstName.getText(), lastName.getText(),
						tcNumber.getText(), emailField.getText(), phonenNumber.getText(), passwordField.getText());
				System.out.println(request);
				int result = passengerRepository.registerPassenger(request);
				if (result != 0) {
					tabbedPane.setSelectedIndex(1);
				} else {
					System.out.println("sıkıntı");
				}

			}
		});

	}
}
