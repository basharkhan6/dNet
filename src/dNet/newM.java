package dNet;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class newM extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					newM frame = new newM(171159102);
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
	public newM(int sID) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\BASHAR\\Desktop\\dNet\\logo.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 300, 273, 183);
		setTitle("Search");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFriendsId = new JLabel("Enter your Recepient ID:");
		lblFriendsId.setFont(new Font("Arial", Font.PLAIN, 18));
		lblFriendsId.setBounds(22, 24, 215, 22);
		contentPane.add(lblFriendsId);
		
		textField = new JTextField();
		textField.setBounds(22, 57, 193, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int noID=1, rID=Integer.parseInt(textField.getText());
				
				try {
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/dnet", "root", "");
					Statement stmnt=conn.createStatement();
					ResultSet rs = stmnt.executeQuery("SELECT id FROM student WHERE id='"+sID+"'");
					while(rs.next()) {
						noID=0;
					}
					conn.close();
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Connection Error in Conn!!!");
					dispose();
				}
				
				String empty="";
				if(textField.getText().equals(empty)) {
					JOptionPane.showMessageDialog(null, "You don't enter any ID");
				}
				else if(noID!=0) {
					JOptionPane.showMessageDialog(null, "ID you enter\nThats not Register.");
					dispose();
				}	
				else {
					chats ob = new chats(sID, rID, 3);
					ob.setVisible(true);
					dispose();
				}
			}
		});
		btnOk.setBounds(22, 91, 89, 34);
		contentPane.add(btnOk);
	}
}
