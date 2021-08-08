package dNet;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class chats extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chats frame = new chats(171159102, 171159122,  2);
					frame.setVisible(true);
					frame.setTitle("Chat   --||-- dNet by RED4");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public chats(int Sender, int Recepient, int Refer) {
		Image img2 = new ImageIcon(this.getClass().getResource("/logo.jpg")).getImage();
		setIconImage(img2);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 480);
		setTitle("Messages");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		//Image img2 = new ImageIcon(this.getClass().getResource("/msg.jpg")).getImage();
		//setContentPane(new JLabel(new ImageIcon(img2)));
		contentPane.setLayout(null);
		
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection conn4=DriverManager.getConnection("jdbc:mysql://localhost/dnet", "root", "");

					String NameSql = "SELECT name FROM student WHERE id='"+Sender+"'";
					java.sql.PreparedStatement NamePst2 = conn4.prepareStatement(NameSql);
					ResultSet NameRs2 = NamePst2.executeQuery();
					while(NameRs2.next()) {
						if(Refer==1) {
							dispose();
						}
						else if(Refer==2) {
							ProfileRqst ob2 =new ProfileRqst(Sender, Recepient);
							ob2.setVisible(true);
							dispose();
						}
						else if(Refer==3) {
							inbox  ob3 = new inbox(Sender);
							ob3.setVisible(true);
							dispose();
						}
						else {
							
						}
					}
					conn4.close();
				}catch(Exception b) {
					System.out.print("Failed in conn4");
				}
				
			}
		});
		btnBack.setBounds(24, 394, 131, 37);
		contentPane.add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 46, 417, 248);
		contentPane.add(scrollPane);
		
		JTextArea taM = new JTextArea();
		taM.setFont(new Font("Arial", Font.PLAIN, 16));
		taM.setEditable(false);
		scrollPane.setViewportView(taM);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane2.setBounds(24, 305, 417, 78);
		contentPane.add(scrollPane2);
		
		JTextArea taM2 = new JTextArea();
		taM2.setFont(new Font("Arial", Font.PLAIN, 14));
		scrollPane2.setViewportView(taM2);
		
		JLabel lblTo = new JLabel();
		lblTo.setForeground(Color.MAGENTA);
		lblTo.setFont(new Font("Arial", Font.PLAIN, 18));
		lblTo.setBounds(24, 11, 36, 35);
		contentPane.add(lblTo);
		
		JLabel lblRecepient = new JLabel();
		lblRecepient.setFont(new Font("Arial", Font.PLAIN, 18));
		lblRecepient.setBounds(58, 9, 229, 37);
	//	lblTo.setText("hjgj");
		contentPane.add(lblRecepient);
		
		//Add Recepient name
		try {
			Connection conn3=DriverManager.getConnection("jdbc:mysql://localhost/dnet", "root", "");

			String NameSql = "SELECT name FROM student WHERE id='"+Recepient+"'";
			java.sql.PreparedStatement NamePst = conn3.prepareStatement(NameSql);
			ResultSet NameRs = NamePst.executeQuery();
			while(NameRs.next()) {
				lblTo.setText("To: ");
				lblRecepient.setText(NameRs.getString("name"));
			}
			conn3.close();
		}catch(Exception b) {
			System.out.print("Failed in conn3");
		}
		
		
		JButton btnLogout = new JButton("LogOut");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				login ob = new login();
				ob.frame.setVisible(true);
				JOptionPane.showMessageDialog(null, "LogOut Successful", "LogOut", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnLogout.setBounds(451, 11, 116, 78);
		contentPane.add(btnLogout);
		
		
		// Get msg
		int totMsg=0;
		try {
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/dnet", "root", "");
			String Asql = "SELECT msg, time FROM chat WHERE (sender = '"+Sender+"' AND recepient = '"+Recepient+"') OR (recepient = '"+Sender+"' AND sender ='"+Recepient+"')";
			java.sql.PreparedStatement Apst = conn.prepareStatement(Asql);
			ResultSet Ars = Apst.executeQuery();
			while(Ars.next()) {
				totMsg++;
			}
			conn.close();
			if(totMsg>0) {
				try {
					Connection conn2=DriverManager.getConnection("jdbc:mysql://localhost/dnet", "root", "");
					
					String sql = "SELECT * FROM chat WHERE (sender = '"+Sender+"' AND recepient = '"+Recepient+"') OR (recepient = '"+Sender+"' AND sender ='"+Recepient+"')";
					java.sql.PreparedStatement pst = conn2.prepareStatement(sql);
					ResultSet rs = pst.executeQuery();

					int i=0;
					String [] Messages = new String[totMsg];
					String [] msgTime = new String[totMsg];
					while(rs.next()) {
						if(rs.getInt("sender")==Sender) {
							Messages[i]="Me:" +rs.getString("msg");
						}
						else {
							Messages[i]="Other: " +rs.getString("msg");
						}
						msgTime[i]=rs.getString("time");
					    taM.append("\n"+Messages[i]+"\n"+msgTime[i]+"\n");
					    i++;
					}
					conn2.close();
				}catch(Exception e) {
					taM.append("Problen in System: MessageBox");
				}
			}
		}catch(Exception e) {
			taM.append("No Message to show");
		}
		
		
		
		
		//Send Msg
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(taM2.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Your Message is Empty!!!", "Empty", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					try{
						String Message = taM2.getText();
						Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/dnet", "root", "");
				
						String bsql = "INSERT INTO `chat` (`msgSID`, `sender`, `recepient`, `msg`, `time`) VALUES (NULL, '"+Sender+"', '"+Recepient+"', '"+Message+"', CURRENT_TIMESTAMP)";
						java.sql.PreparedStatement pst = conn.prepareStatement(bsql);
						pst.executeUpdate();
						conn.close();
						
					}catch(Exception e) {
						JOptionPane.showMessageDialog(null, "Error: Message Can't be Send");
					}
					
					taM.setText("");
					
					//Get all msg Again
					int totMsg2=0;
					try {
						Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/dnet", "root", "");
						String Asql = "SELECT msg, time FROM chat WHERE (sender = '"+Sender+"' AND recepient = '"+Recepient+"') OR (recepient = '"+Sender+"' AND sender ='"+Recepient+"')";
						java.sql.PreparedStatement Apst = conn.prepareStatement(Asql);
						ResultSet Ars = Apst.executeQuery();
						while(Ars.next()) {
							totMsg2++;
						}
						conn.close();
						if(totMsg2>0) {
							try {
								Connection conn2=DriverManager.getConnection("jdbc:mysql://localhost/dnet", "root", "");
								
								String sql = "SELECT * FROM chat WHERE (sender = '"+Sender+"' AND recepient = '"+Recepient+"') OR (recepient = '"+Sender+"' AND sender ='"+Recepient+"')";
								java.sql.PreparedStatement pst = conn2.prepareStatement(sql);
								ResultSet rs = pst.executeQuery();

								int i=0;
								String [] Messages = new String[totMsg2];
								String [] msgTime = new String[totMsg2];
								while(rs.next()) {
									if(rs.getInt("sender")==Sender) {
										Messages[i]="Me:" +rs.getString("msg");
									}
									else {
										Messages[i]="Other: " +rs.getString("msg");
									}
									msgTime[i]=rs.getString("time");
								    taM.append("\n"+Messages[i]+"\n"+msgTime[i]+"\n");
								    i++;
								}
								conn2.close();
							}catch(Exception e) {
								taM.setText("Error: Message Send but \nProblen in System: MessageBox");
							}
						}
					}catch(Exception e) {
						taM.setText("Error: Message Send but....");
					}
					
				}
				
				//Clear All
				taM2.setText("");
			}
		});
		btnSend.setBounds(451, 305, 116, 78);
		contentPane.add(btnSend);
		
		
		
		//ReLoad

		JButton btnReload = new JButton("Re");
		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				taM.setText("");
				//Get all msg Again
				int totMsg2=0;
				try {
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/dnet", "root", "");
					String Asql = "SELECT msg, time FROM chat WHERE (sender = '"+Sender+"' AND recepient = '"+Recepient+"') OR (recepient = '"+Sender+"' AND sender ='"+Recepient+"')";
					java.sql.PreparedStatement Apst = conn.prepareStatement(Asql);
					ResultSet Ars = Apst.executeQuery();
					while(Ars.next()) {
						totMsg2++;
					}
					conn.close();
					if(totMsg2>0) {
						try {
							Connection conn2=DriverManager.getConnection("jdbc:mysql://localhost/dnet", "root", "");
							String sql = "SELECT * FROM chat WHERE (sender = '"+Sender+"' AND recepient = '"+Recepient+"') OR (recepient = '"+Sender+"' AND sender ='"+Recepient+"')";
							java.sql.PreparedStatement pst = conn2.prepareStatement(sql);
							ResultSet rs = pst.executeQuery();

							int i=0;
							String [] Messages = new String[totMsg2];
							String [] msgTime = new String[totMsg2];
							while(rs.next()) {
								if(rs.getInt("sender")==Sender) {
									Messages[i]="Me:" +rs.getString("msg");
								}
								else {
									Messages[i]="Other: " +rs.getString("msg");
								}
								msgTime[i]=rs.getString("time");
							    taM.append("\n"+Messages[i]+"\n"+msgTime[i]+"\n");
							    i++;
							}
							conn2.close();
						}catch(Exception e) {
							taM.setText("Error: Message Send but \nProblen in System: MessageBox");
						}
					}
				}catch(Exception e) {
					taM.setText("Error: Message Send but....");
				}
				
				//Clear All
				taM2.setText("");
				
			}
		});
		btnReload.setBounds(492, 394, 75, 37);
		contentPane.add(btnReload);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\BASHAR\\Desktop\\dNet\\img\\msg.jpg"));
		label.setBackground(Color.WHITE);
		label.setBounds(0, 0, 594, 452);
		contentPane.add(label);
	}
}
