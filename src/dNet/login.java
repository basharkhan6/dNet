package dNet;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.sql.*;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class login {
	

	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("");
		frame.getContentPane().setBackground(new Color(211, 211, 211));
		frame.getContentPane().setFont(new Font("Agency FB", Font.BOLD, 24));
		frame.setBounds(100, 100, 720, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("LogIn --||-- dNet by RED4");
		Image img2 = new ImageIcon(this.getClass().getResource("/logo.jpg")).getImage();
		frame.setIconImage(img2);
		
		
		JLabel lblID = new JLabel("Student ID:");
		lblID.setFont(new Font("Agency FB", Font.BOLD, 24));
		lblID.setBounds(135, 239, 96, 36);
		frame.getContentPane().add(lblID);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Agency FB", Font.BOLD, 24));
		lblPassword.setBounds(135, 291, 96, 34);
		frame.getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setFont(new Font("Agency FB", Font.BOLD, 18));
		textField.setBounds(241, 241, 68, 39);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Agency FB", Font.BOLD, 18));
		textField_1.setBounds(319, 241, 50, 39);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Agency FB", Font.BOLD, 18));
		textField_2.setBounds(379, 241, 105, 39);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Agency FB", Font.BOLD, 24));
		passwordField.setBounds(241, 291, 243, 36);
		frame.getContentPane().add(passwordField);
		
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int lid1, lid2, lid3;
				
				try {
					lid1=Integer.parseInt(textField.getText());
					lid2=Integer.parseInt(textField_1.getText());
	
					lid3=Integer.parseInt(textField_2.getText());
					String pass = new String(passwordField.getPassword());
					
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/dnet", "root", "");
					Statement stmnt=conn.createStatement();
					ResultSet rs = stmnt.executeQuery("SELECT * FROM student WHERE (id1='"+lid1+"') AND (id2='"+lid2+"') AND (id3='"+lid3+"') AND (Password='"+pass+"')");
					while(rs.next()) {
						
						if(rs.getInt("ID1")!=lid1 && rs.getInt("ID2")!=lid2 && rs.getInt("ID3")!= lid3) {
							JOptionPane.showMessageDialog(null, "No account created at this id\nPlease create a account first");
							textField.setText("");
							textField_1.setText("");
							textField_2.setText("");
							passwordField.setText("");
						}
						else if (!(rs.getString("Password").equals(pass))) {
							JOptionPane.showMessageDialog(null, "Password Error");
							passwordField.setText("");
						}
						else {
							Profile ob = new Profile(rs.getInt("id"));
							ob.setVisible(true);
							frame.dispose();
						}
					}
					conn.close();
					
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Connection Fail!!");
				}
			}
		});
		btnLogin.setFont(new Font("Agency FB", Font.BOLD, 26));
		btnLogin.setBounds(241, 338, 105, 46);
		frame.getContentPane().add(btnLogin);
		
		JButton btnCreateNewAccount = new JButton("Create New Account");
		btnCreateNewAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				register ob = new register();
				ob.setVisible(true);
			}
		});
		btnCreateNewAccount.setFont(new Font("Agency FB", Font.BOLD, 24));
		btnCreateNewAccount.setBounds(241, 395, 243, 36);
		frame.getContentPane().add(btnCreateNewAccount);
		
		JLabel lblNewLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/logo.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(281, 11, 146, 146);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblWelcomeTo = new JLabel("Welcome to");
		lblWelcomeTo.setFont(new Font("Arial", Font.ITALIC, 28));
		lblWelcomeTo.setBounds(226, 181, 172, 47);
		frame.getContentPane().add(lblWelcomeTo);
		
		JLabel lblD = new JLabel("d");
		lblD.setForeground(new Color(0, 0, 139));
		lblD.setFont(new Font("World of Water", Font.PLAIN, 34));
		lblD.setBounds(391, 184, 29, 36);
		frame.getContentPane().add(lblD);
		
		JLabel lblNet = new JLabel("Net");
		lblNet.setForeground(new Color(0, 255, 0));
		lblNet.setFont(new Font("Gabriola", Font.BOLD, 38));
		lblNet.setBounds(408, 186, 68, 42);
		frame.getContentPane().add(lblNet);
		
		JButton btnForgot = new JButton("Forget Password?");
		btnForgot.setFont(new Font("Agency FB", Font.PLAIN, 17));
		btnForgot.setBounds(356, 338, 128, 46);
		frame.getContentPane().add(btnForgot);
		
	}
}

