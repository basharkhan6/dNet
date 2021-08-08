package dNet;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Profile extends JFrame {

	private JPanel contentPane;
	private Object String;
	
	
	String Name=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Profile frame = new Profile(171159102);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Profile(int pID) {
		
		Image img2 = new ImageIcon(this.getClass().getResource("/logo.jpg")).getImage();
		setIconImage(img2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 461);
		setTitle("Profile --||-- dNet by RED4");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblName.setBounds(27, 42, 68, 25);
		contentPane.add(lblName);
		
		JLabel label = new JLabel();
		label.setBounds(97, 42, 162, 25);
		contentPane.add(label);
		
		JLabel lblStudentId = new JLabel("Student ID:");
		lblStudentId.setBounds(27, 92, 68, 25);
		contentPane.add(lblStudentId);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(97, 92, 173, 25);
		contentPane.add(lblNewLabel);
		
		
		try {
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/dnet", "root", "");
			
			String sql = "SELECT * FROM student WHERE ID='"+pID+"'";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				setName(rs.getString("name"));
			}
			
			conn.close();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Please Login Again!");
			dispose();
			login ob = new login();
			ob.frame.setVisible(true);
		}
		
		
		JButton btnFriends = new JButton("Friends");
		btnFriends.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				friends frameF = new friends(pID, Name);
				frameF.setVisible(true);
			}
		});
		btnFriends.setBounds(37, 354, 89, 23);
		contentPane.add(btnFriends);
		
		JButton btnRequest = new JButton("Request");
		btnRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				request window=new request(pID);
				window.setVisible(true);
				dispose();
			}
		});
		btnRequest.setBounds(160, 354, 89, 23);
		contentPane.add(btnRequest);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				login ob = new login();
				ob.frame.setVisible(true);
				JOptionPane.showMessageDialog(null, "LogOut Successful", "LogOut", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnLogout.setBounds(335, 45, 89, 23);
		contentPane.add(btnLogout);
		
		JButton btnInbox = new JButton("Inbox");
		btnInbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				inbox ob = new inbox(pID);
				ob.setVisible(true);
			}
		});
		btnInbox.setBounds(280, 354, 89, 23);
		contentPane.add(btnInbox);
		
		JLabel lblDepartment = new JLabel("Department:");
		lblDepartment.setBounds(26, 139, 78, 14);
		contentPane.add(lblDepartment);
		
		JLabel lblBatch = new JLabel("Batch:");
		lblBatch.setBounds(27, 180, 46, 14);
		contentPane.add(lblBatch);
		
		JLabel lblFaculty = new JLabel("Faculty:");
		lblFaculty.setBounds(27, 220, 46, 14);
		contentPane.add(lblFaculty);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(114, 139, 89, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(97, 180, 60, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setBounds(97, 220, 272, 14);
		contentPane.add(label_3);
		
		JLabel lblEnrollment = new JLabel("Enrollment:");
		lblEnrollment.setBounds(27, 254, 68, 14);
		contentPane.add(lblEnrollment);
		
		JLabel label_4 = new JLabel("");
		label_4.setBounds(97, 254, 162, 14);
		contentPane.add(label_4);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(27, 290, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel label_5 = new JLabel("");
		label_5.setBounds(70, 289, 260, 16);
		contentPane.add(label_5);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(335, 286, 89, 23);
		contentPane.add(btnUpdate);
		
		
		try {
			int dept=0, batch=0;
			
			Connection conn2=DriverManager.getConnection("jdbc:mysql://localhost/dnet", "root", "");
			
			String bsql = "SELECT * FROM student WHERE ID='"+pID+"'";
			java.sql.PreparedStatement pst = conn2.prepareStatement(bsql);
			ResultSet rs2 = pst.executeQuery();
			
			while(rs2.next()) {
				label.setText(rs2.getString("name"));
				lblNewLabel.setText(rs2.getString("id1")+"-"+rs2.getString("id2")+"-"+rs2.getString("id3"));
				label_5.setText(rs2.getString("email"));
				dept = rs2.getInt("id2");
				batch=rs2.getInt("id1");
			}
			switch(dept) {
			case 15:
				label_1.setText("CSE");
				label_3.setText("Information Science and Technology");
				break;
			case 35:
				label_1.setText("SWE");
				label_3.setText("Information Science and Technology");
				break;
			case 29:
				label_1.setText("ENG");
				label_3.setText("Unknown");
				break;
			default:
				label_1.setText("Unknown");
				label_3.setText("Unknown");
			}
			
			
			switch(batch) {
			case 163:
				label_2.setText("45");
				label_4.setText("Fall 2016");
				break;
			case 171:
				label_2.setText("46");
				label_4.setText("Spring 2017");
				break;
			case 172:
				label_2.setText("47");
				label_4.setText("Summer 2017");
				break;
			default:
				label_2.setText("Unknown");
			}
			
			conn2.close();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Please Login Again!");
			dispose();
			login ob = new login();
			ob.frame.setVisible(true);
		}

		
	}
	
	
	
	public String getName() {
		return Name;
	}

	public void setName(String Name){
		this.Name=Name;
	}
}


