import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.awt.BorderLayout;


public class TestConnection extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtFieldNom;
	private JTextField textFieldPrenom;
	private Connect maBd= new Connect("G221_B_BD1","G221_B","G221_B");
	private JComboBox comboBox;
	
	public static void main(String[] args) {
		TestConnection frame = new TestConnection();
		frame.UpdateList();
    
        Calendar cal = new GregorianCalendar(2015, Calendar.JANUARY, 1);        
        // Formatage de la Date pour affichage
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Date: " + dateFormat.format(cal.getTime()));

        /*
        maBd.displayAllShots();
        maBd.displayAllUsers();
        maBd.setUser("Jouve","Remi");
        System.out.println(maBd.getId());
        maBd.addNewUser("Carl","Charles");
        maBd.addShot(505,1,2,3,"C");
        maBd.displayAllUsers();
        maBd.displayAllShots();

		 */
	}

	
	public TestConnection() {
		setTitle("BDB");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1073, 646);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tabbedPane.setBounds(0, 0, 1057, 607);
		contentPane.add(tabbedPane);
		
		JPanel panelConnection = new JPanel();
		tabbedPane.addTab("Connection", null, panelConnection, "");
		panelConnection.setLayout(null);

		JPanel panelTirManuel = new JPanel();

		
		JPanel oldUserPanel = new JPanel();
		oldUserPanel.setBounds(519, 0, 533, 579);
		panelConnection.add(oldUserPanel);
		oldUserPanel.setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setBounds(113, 82, 307, 43);
		oldUserPanel.add(comboBox);
		
		JLabel lblOldUser = new JLabel("Utilisateur existant :");
		lblOldUser.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblOldUser.setBounds(113, 21, 307, 28);
		oldUserPanel.add(lblOldUser);
		
		JButton btnOldUser = new JButton("se connecter");
		btnOldUser.setBounds(151, 450, 307, 70);
		oldUserPanel.add(btnOldUser);
		btnOldUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				int index = comboBox.getSelectedIndex()+1;
				maBd.setUser(index);
				tabbedPane.setSelectedIndex(2);
			}
		});
		btnOldUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JPanel newUserPanel = new JPanel();
		newUserPanel.setBounds(0, 0, 509, 579);
		panelConnection.add(newUserPanel);
		newUserPanel.setLayout(null);
		
		txtFieldNom = new JTextField();
		txtFieldNom.setText("Jouve");
		txtFieldNom.setFont(new Font("Tahoma", Font.PLAIN, 30));
		txtFieldNom.setBounds(191, 67, 251, 38);
		newUserPanel.add(txtFieldNom);
		txtFieldNom.setColumns(10);
		
		textFieldPrenom = new JTextField();
		textFieldPrenom.setFont(new Font("Tahoma", Font.PLAIN, 30));
		textFieldPrenom.setText("Remi");
		textFieldPrenom.setBounds(191, 206, 251, 38);
		newUserPanel.add(textFieldPrenom);
		textFieldPrenom.setColumns(10);
		
		JLabel lblNom = new JLabel("Nom :");
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNom.setBounds(10, 72, 171, 28);
		newUserPanel.add(lblNom);
		
		JLabel lblPrnom = new JLabel("Pr\u00E9nom :");
		lblPrnom.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblPrnom.setBounds(10, 204, 171, 28);
		newUserPanel.add(lblPrnom);
		
		JButton btnNewUser = new JButton("cr\u00E9er un compte");
		btnNewUser.setBounds(80, 450, 307, 70);
		newUserPanel.add(btnNewUser);
		btnNewUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(maBd.getId( txtFieldNom.getText(), textFieldPrenom.getText() ) !=-1) {// utilisateur existe deja
					JOptionPane.showMessageDialog(null, " Cet Utilisateur existe d\u00E9ja", "BDB : Utilisateur existant" , JOptionPane.INFORMATION_MESSAGE);
				}else {//utilisateur ajoute
					maBd.addNewUser( txtFieldNom.getText(), textFieldPrenom.getText() );
					JOptionPane.showMessageDialog(null, " Utilisateur ajout\u00E9", "BDB : Utilisateur cr\u00E9\u00E9" , JOptionPane.INFORMATION_MESSAGE);
					tabbedPane.setSelectedIndex(2);
				}
				UpdateList();
			}
		});
		btnNewUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JPanel Tir_manuel = new JPanel();
		tabbedPane.addTab("Tir manuel", null, Tir_manuel, null);
		
		JPanel Tir_auto = new JPanel();
		tabbedPane.addTab("Tir auto", null, Tir_auto, null);
		Tir_auto.setLayout(null);
		
		JPanel panelDonneA = new JPanel();
		panelDonneA.setBounds(0, 0, 433, 571);
		Tir_auto.add(panelDonneA);
		panelDonneA.setLayout(new BorderLayout(0, 0));
		
		JTextArea textArea_1 = new JTextArea();
		panelDonneA.add(textArea_1, BorderLayout.CENTER);
		
		JPanel panel_cibleA = new JPanel();
		panel_cibleA.setBounds(455, 0, 571, 571);
		panel_cibleA.setLayout(null);
		Tir_auto.add(panel_cibleA);
		
		JPanel panelStats = new JPanel();
		tabbedPane.addTab("Statistiques", null, panelStats, null);
		setVisible(true);
		Tir_manuel.setLayout(null);
		
		JPanel panelDonne = new JPanel();
		panelDonne.setBounds(0, 0, 433, 571);
		Tir_manuel.add(panelDonne);
		panelDonne.setLayout(new BorderLayout(0, 0));
		
		JTextArea textArea = new JTextArea();
		panelDonne.add(textArea);
		
		JPanel panel_cible = new JPanel();
		panel_cible.setBounds(455, 0, 571, 571);
		panel_cible.setLayout(null);
		Tir_manuel.add(panel_cible);
		
		
				Target cibleM = new Target(panel_cible);
				panel_cible.add(cibleM);

				Target cibleA = new Target(panel_cibleA);
				panel_cibleA.add(cibleA);
	}

	public void UpdateList(){
		LinkedList<Utilisateur> UserList= maBd.getAllUsers();
		comboBox.removeAllItems();
		for (Utilisateur x: UserList)
			comboBox.addItem(x.getNom()+" "+x.getPrenom());
	}
}
