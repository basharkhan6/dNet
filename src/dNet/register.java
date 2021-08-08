package dNet;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class register extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JLabel lblEmail;
	private JTextField textField_5;
	private JButton btnBack;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					register frame = new register();
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
	public register() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\BASHAR\\Desktop\\dNet\\logo.jpg"));
		setTitle("New Member Registration --||-- dNet by RED4");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStudentId = new JLabel("Student ID:");
		lblStudentId.setBounds(10, 35, 66, 23);
		contentPane.add(lblStudentId);
		
		textField = new JTextField();
		textField.setBounds(79, 36, 130, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(105, 80, 58, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblIdInParts = new JLabel("ID in Parts");
		lblIdInParts.setBounds(10, 83, 72, 14);
		contentPane.add(lblIdInParts);
		
		textField_2 = new JTextField();
		textField_2.setBounds(173, 80, 36, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(219, 80, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 162, 46, 14);
		contentPane.add(lblName);
		
		textField_4 = new JTextField();
		textField_4.setBounds(79, 159, 130, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 212, 66, 14);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(79, 209, 130, 20);
		contentPane.add(passwordField);
		
		JLabel lblRepeat = new JLabel("Repeat:");
		lblRepeat.setBounds(10, 255, 46, 14);
		contentPane.add(lblRepeat);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(79, 252, 130, 20);
		contentPane.add(passwordField_1);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int lid=0, lid1, lid2, lid3, ok=0;
				String name, email, pass1, pass2;
				
					lid=Integer.parseInt(textField.getText());
					lid1=Integer.parseInt(textField_1.getText());
					lid2=Integer.parseInt(textField_2.getText());
					lid3=Integer.parseInt(textField_3.getText());
					name=textField_4.getText();
					email=textField_5.getText();
					
					pass1 = new String(passwordField.getPassword());
					pass2 = new String(passwordField_1.getPassword());
					
					try {
						Connection conn1=DriverManager.getConnection("jdbc:mysql://localhost/dnet", "root", "");
						Statement stmnt=conn1.createStatement();
						ResultSet rs = stmnt.executeQuery("select * from Student");
						while(rs.next()) {
							if(rs.getInt("ID")==lid) {
								ok=0;
								JOptionPane.showMessageDialog(null, "Account already created");
							}
							else if(rs.getString("Email").equals(email)) {
								ok=0;
								JOptionPane.showMessageDialog(null, "Email already have in other account");
							}
							
							
						}
						if(pass1.equals("") || email.equals("") || name.equals("") || lid==0) {
							ok=0;
							JOptionPane.showMessageDialog(null, "Fill all the field");
						}
						else if(pass1.equals(pass2)) {
							ok=1;
						}
						else {
							ok=0;
							JOptionPane.showMessageDialog(null, "Password not Mached");
						}
							
					}catch(Exception e1) {
						JOptionPane.showMessageDialog(null, "Connection Fail");
						dispose();
						login ob=new login();
						ob.frame.setVisible(true);
					}
			
			if(ok==1) {
				try {
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/dnet", "root", "");
					
					String sql = "INSERT INTO `student` (`ID`, `ID1`, `ID2`, `ID3`, `Name`, `Password`, `Email`) VALUES ( '"+ lid+"', '"+lid1+"', '"+lid2+"', '"+lid3+"', '"+name+"', '"+pass1+"', '"+email+"')";
					java.sql.PreparedStatement pst = conn.prepareStatement(sql);
					pst.executeUpdate();
					conn.close();
					
					JOptionPane.showMessageDialog(null, "New Profile Created");
					dispose();
					login ob=new login();
					ob.frame.setVisible(true);
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Can't Crate new Profile");
					dispose();
					login ob=new login();
					ob.frame.setVisible(true);
				}
				}
			else {
				JOptionPane.showMessageDialog(null, "Can't Crate new Profile");
			}
			}
		});
		btnSubmit.setBounds(79, 361, 89, 51);
		contentPane.add(btnSubmit);
		
		lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 301, 46, 14);
		contentPane.add(lblEmail);
		
		textField_5 = new JTextField();
		textField_5.setBounds(79, 298, 130, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				login ob=new login();
				ob.frame.setVisible(true);
			}
		});
		btnBack.setBounds(216, 375, 89, 23);
		contentPane.add(btnBack);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\BASHAR\\Desktop\\dNet\\img\\msg.jpg"));
		label.setBounds(0, 0, 584, 442);
		contentPane.add(label);
	}
}
