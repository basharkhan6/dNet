package dNet;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class inbox extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inbox frame = new inbox(171159102);
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
	public inbox(int sID) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\BASHAR\\Desktop\\dNet\\logo.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 614, 495);
		setTitle("Inbox --||-- dNet by RED4");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 45, 536, 367);
		contentPane.add(scrollPane);
		
		JPanel panel = new JPanel(new GridLayout(6,1,10,6));		//(no of Rows, no of Cols, horizental gap, verical gap) all in int
		panel.setBackground(Color.WHITE);
		scrollPane.setViewportView(panel);
		
		JButton btnNew = new JButton("Write");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newM aOB=new newM(sID);
				aOB.setVisible(true);
				dispose();
			}
		});
		btnNew.setBounds(473, 11, 89, 23);
		contentPane.add(btnNew);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Profile ob=new Profile(sID);
				ob.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(26, 423, 89, 23);
		contentPane.add(btnBack);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\BASHAR\\Desktop\\dNet\\img\\msg.jpg"));
		label.setBounds(0, 0, 603, 459);
		contentPane.add(label);
		
		
		int mSize=0;
		
		try {
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/dnet", "root", "");
			Statement stmnt=conn.createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT * FROM chat WHERE recepient='"+sID+"' OR sender='"+sID+"'");
			while(rs.next()) {
				mSize++;
				}
			conn.close();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "No Message");
		}

		String [] fArray= new String[mSize];	//Store msg
		int [] fArray2= new int [mSize];	// Store others ID
		String [] fArray3= new String [mSize]; 	//Store others name
		int j =0;
		  try {
			  Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/dnet", "root", "");
				Statement stmnt=conn.createStatement();
				ResultSet rs = stmnt.executeQuery("SELECT * FROM chat WHERE recepient='"+sID+"' OR sender='"+sID+"' ORDER BY msgSID DESC");
				while(rs.next()) {
					mSize++;
						fArray[j]=rs.getString("msg");
						if(rs.getInt("sender")==sID) {
							fArray2[j]=rs.getInt("recepient");
						}
						else {
							fArray2[j]=rs.getInt("sender");
						}
						j++;
				}
				conn.close();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Input Error in ID");
		}
		
		  int aID;
		for(int i=0; i<=mSize; i++) {
			
			try {
				 aID=fArray2[i];
				
				  Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/dnet", "root", "");
					Statement stmnt=conn.createStatement();
					ResultSet rs = stmnt.executeQuery("SELECT name FROM student WHERE id='"+aID+"'");
					while(rs.next()) {
						fArray3[i]=rs.getString("name");
					}
					conn.close();
			}catch(Exception e) {
				//JOptionPane.showMessageDialog(null, "Input Error in ID2");
			}
		}
		
		  
		 
		
		
		
		// Create an array of Button type Objects
		Button [] fButton = new Button[fArray.length];
		

		int k=10;
		// Use for loop to instantiate every button object
        for(int i = 0; i <fArray.length; ++i){
            fButton[i] = new Button(fArray3[i]+"\n---"+fArray[i]);
            fButton[i].addActionListener(new passI(i, sID, fArray.length, fArray2 ){
            	//pass variable in self crate class
            		}
            		);
            k=k+33;
        }
        
     // Add all of the buttons to the layout
        for(int i = 0; i < fArray.length; ++i) {
            panel.add(fButton[i]);
        }
        
	}
}



class passI implements ActionListener{
    int index, sID, fSize;
    int fArray2[]= new int[fSize];

    passI(int index, int ID, int fSize, int fArray2[]) {
        this.index = index;
        this.sID=ID;
        this.fSize=fSize;
        this.fArray2=fArray2;
        
    }

    public void actionPerformed(ActionEvent e) {
    	int rID= fArray2[index];
    	//System.out.print(rID);
		chats frame = new chats(sID, rID, 3);
		frame.setVisible(true);
		
    }
}
