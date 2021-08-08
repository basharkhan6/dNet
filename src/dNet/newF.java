package dNet;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class newF extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					newF frame = new newF(171159102, "Abul Bashar");
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
	public newF(int sID, String Name) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\BASHAR\\Desktop\\dNet\\logo.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 300, 273, 183);
		setTitle("Search");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFriendsId = new JLabel("Enter your friends ID:");
		lblFriendsId.setFont(new Font("Arial", Font.PLAIN, 18));
		lblFriendsId.setBounds(22, 24, 169, 22);
		contentPane.add(lblFriendsId);
		
		textField = new JTextField();
		textField.setBounds(22, 57, 193, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int noID=1, alreadyR=0, alreadyO=0, alreadyF=0, fID=Integer.parseInt(textField.getText());
				try {
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/dnet", "root", "");
					Statement stmnt=conn.createStatement();
					ResultSet rs = stmnt.executeQuery("SELECT id FROM student WHERE id='"+fID+"'");
					while(rs.next()) {
						noID=0;
					}
					conn.close();
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Connection Error in Conn!!!");
					dispose();
				}
				
				try {
					Connection conn2=DriverManager.getConnection("jdbc:mysql://localhost/dnet", "root", "");
					Statement stmnt2=conn2.createStatement();
					ResultSet rs2 = stmnt2.executeQuery("SELECT * FROM request WHERE sID='"+sID+"' AND fID='"+fID+"'");
					while(rs2.next()) {
						alreadyR=1;
					}
					conn2.close();
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Connection Error in Conn2!!!");
					dispose();
				}
				
				try {
					Connection conn3=DriverManager.getConnection("jdbc:mysql://localhost/dnet", "root", "");
					Statement stmnt3=conn3.createStatement();
					ResultSet rs3 = stmnt3.executeQuery("SELECT * FROM request WHERE sID='"+sID+"' AND fID='"+fID+"'");
					while(rs3.next()) {
						alreadyO=1;
					}
					conn3.close();
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Connection Error in Conn3!!!");
					dispose();
				}
				
				try {
					Connection conn4=DriverManager.getConnection("jdbc:mysql://localhost/dnet", "root", "");
					Statement stmnt4=conn4.createStatement();
					ResultSet rs4 = stmnt4.executeQuery("SELECT id1, id2 FROM friends WHERE id1='"+sID+"' AND id2='"+fID+"'");
					while(rs4.next()) {
						alreadyF=1;
					}
					conn4.close();
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Connection Error in Conn4!!!");
					dispose();
				}
				
				String empty="";
				if(textField.getText().equals(empty)) {
					JOptionPane.showMessageDialog(null, "You don't enter any ID");
				}
				else if(sID==fID) {
					JOptionPane.showMessageDialog(null, "You enter your Own ID");
					textField.setText("");
				}
				else if(noID!=0) {
					JOptionPane.showMessageDialog(null, "ID you enter\nThats not Register.");
					dispose();
				}
				else if(alreadyR==1) {
					JOptionPane.showMessageDialog(null, "You already send a Request at this ID");
					dispose();
					}
				else if(alreadyO==1) {
					JOptionPane.showMessageDialog(null, "Your friend already sent a Request\nPlease check in Request");
					dispose();
				}
				else if(alreadyF==1) {
					JOptionPane.showMessageDialog(null, "You are already friends");
					dispose();
				}
				
				
				else {
					try{
						Connection conn5=DriverManager.getConnection("jdbc:mysql://localhost/dnet", "root", "");
				
						String bsql = "INSERT INTO `request` (`sID`, `fID`,`name`) VALUES ('"+sID+"', '"+fID+"', '"+Name+"')";
						java.sql.PreparedStatement pst = conn5.prepareStatement(bsql);
						pst.executeUpdate();
						
						
						JOptionPane.showMessageDialog(null, "Friend Rquest Send");
						conn5.close();
						dispose();
						
					}catch(Exception e) {
						JOptionPane.showMessageDialog(null, "Connection Fail in Conn5");
						dispose();
					}
				}
			}
		});
		btnOk.setBounds(22, 91, 89, 34);
		contentPane.add(btnOk);
	}
}
