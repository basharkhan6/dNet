package dNet;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class request extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					request frame = new request(171159122);
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
	public request(int ID) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\BASHAR\\Desktop\\dNet\\logo.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 414, 480);
		setTitle("Friend Request --||-- dNet by RED4");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 28, 378, 334);
		contentPane.add(scrollPane);
		
		JPanel panel = new JPanel(new GridLayout(10,1,0,6));		//(no of Rows, no of Cols, horizental gap, verical gap) all in int
		panel.setBackground(Color.WHITE);
		scrollPane.setViewportView(panel);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Profile ob= new Profile(ID);
				ob.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(10, 385, 89, 46);
		contentPane.add(btnBack);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\BASHAR\\Desktop\\dNet\\img\\msg.jpg"));
		label.setBounds(0, 0, 398, 442);
		contentPane.add(label);
		
		
		int rSize=0;
		
		try {
			Connection conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/dnet", "root", "");
			Statement stmnt=conn.createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT fID FROM request WHERE fID='"+ID+"'");
			while(rs.next()) {
					rSize++;
				}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "No Request");
		}

		String [] fArray= new String[rSize];
		int [] fArray2= new int [rSize];
		int j =0;
		
		try {
			Connection conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/dnet", "root", "");
			Statement stmnt=conn.createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT sID, name FROM request WHERE fID='"+ID+"'");
			while(rs.next()) {
						fArray[j]=rs.getString("name");
						fArray2[j]=rs.getInt("sID");
						j++;
					}			
		}catch(Exception e) {
			dispose();
		}
		 
		
		
		
		// Create an array of Button type Objects
		Button [] fButton = new Button[fArray.length];
		

		int k=10;
		// Use for loop to instantiate every button object
        for(int i = 0; i <fArray.length; ++i){
            fButton[i] = new Button(fArray[i]);
            //fButton[i].setBounds(20,k,70,30);
            fButton[i].addActionListener(new passR(i, ID, fArray.length, fArray2 ){
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



class passR implements ActionListener{
    int index, sID, rSize;
    int fArray2[]= new int[rSize];

    passR(int index, int ID, int rSize, int fArray2[]) {
        this.index = index;
        this.sID=ID;
        this.rSize=rSize;
        this.fArray2=fArray2;
        
    }

    public void actionPerformed(ActionEvent e) {
    	int rID= fArray2[index];
    	//System.out.print(rID);
		ProfileRqst frame = new ProfileRqst(sID, rID);
		frame.setVisible(true);
    }
	}

