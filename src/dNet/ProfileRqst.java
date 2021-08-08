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

public class ProfileRqst extends JFrame {

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
					ProfileRqst frame = new ProfileRqst(171159102, 171159122);
					frame.setVisible(true);
					frame.setTitle("Profile --||-- dNet by RED4");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public ProfileRqst(int ID, int ID2) {
		
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
			
			String sql = "SELECT * FROM student WHERE ID='"+ID2+"'";
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
		
		
		JButton btnAccept = new JButton("<html>Accept<br/>Request</html>");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String name2 = null;
					int si = 0;
					Connection conn3=DriverManager.getConnection("jdbc:mysql://localhost/dnet", "root", "");
					
					String sql = "SELECT * FROM student WHERE id='"+ID+"'";
					java.sql.PreparedStatement pst = conn3.prepareStatement(sql);
					ResultSet rs = pst.executeQuery();
					while(rs.next()) {
						name2=rs.getString("name");
					}
					pst.close();
					
					String sql2 = "SELECT * FROM request WHERE sID='"+ID2+"'";
					java.sql.PreparedStatement pst2 = conn3.prepareStatement(sql2);
					ResultSet rs2 = pst2.executeQuery();
					while(rs2.next()) {
						si=Integer.parseInt(rs2.getString("si"));
					}
					pst2.close();
					
					String sql3 = "INSERT INTO `friends` (`id1`, `id2`,`name`) VALUES ('"+ID+"', '"+ID2+"', '"+Name+"')";
					java.sql.PreparedStatement pst3 = conn3.prepareStatement(sql3);
					pst3.executeUpdate();
					
					String sql4 = "INSERT INTO `friends` (`id1`, `id2`,`name`) VALUES ('"+ID2+"', '"+ID+"', '"+name2+"')";
					java.sql.PreparedStatement pst4 = conn3.prepareStatement(sql4);
					pst4.executeUpdate();
					
					String sql5 = "DELETE FROM `request` WHERE si='"+si+"'";
					java.sql.PreparedStatement pst5 = conn3.prepareStatement(sql5);
					pst5.executeUpdate();
					conn3.close();
					
					btnAccept.setVisible(false);
					
					
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Fail in Accepting Request");
					dispose();
				}
				
			}
		});
		btnAccept.setBounds(314, 29, 89, 52);
		contentPane.add(btnAccept);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnBack.setBounds(27, 354, 89, 23);
		contentPane.add(btnBack);
		
		JButton btnChat = new JButton("Chat");
		btnChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chats ob2=new chats(ID, ID2,2);
				ob2.setVisible(true);
				dispose();
			}
		});
		btnChat.setBounds(280, 354, 89, 23);
		contentPane.add(btnChat);
		
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
		
		
		try {
			int dept=0, batch=0;
			
			Connection conn2=DriverManager.getConnection("jdbc:mysql://localhost/dnet", "root", "");
			
			String bsql = "SELECT * FROM student WHERE ID='"+ID2+"'";
			java.sql.PreparedStatement pst = conn2.prepareStatement(bsql);
			ResultSet rs2 = pst.executeQuery();
			
			while(rs2.next()) {
				label.setText(rs2.getString("name"));
				lblNewLabel.setText(rs2.getString("id1")+"-"+rs2.getString("id2")+"-"+rs2.getString("id3"));
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


