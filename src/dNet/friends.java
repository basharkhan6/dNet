package dNet;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class friends extends JFrame{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					friends frame = new friends(171159102, "Bashar Khan");
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
	public friends(int sID, String Name) {
		Image img2 = new ImageIcon(this.getClass().getResource("/logo.jpg")).getImage();
		setIconImage(img2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 272, 497);
		setTitle("Friends --||-- dNet by RED4");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 11, 210, 372);
		contentPane.add(scrollPane);
		
		JPanel panel = new JPanel(new GridLayout(10,1,0,6));		//(no of Rows, no of Cols, horizental gap, verical gap) all in int
		panel.setBackground(Color.WHITE);
		scrollPane.setViewportView(panel);
		
		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newF window= new newF(sID, Name);
				window.setVisible(true);
			}
		});
		btnNew.setBounds(157, 413, 89, 23);
		contentPane.add(btnNew);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Profile ob=new Profile(sID);
				ob.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(10, 413, 89, 23);
		contentPane.add(btnBack);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\BASHAR\\Desktop\\dNet\\img\\msg.jpg"));
		label.setBounds(0, 0, 256, 459);
		contentPane.add(label);
		
		
		int fSize=0;
		
		try {
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/dnet", "root", "");
			Statement stmnt=conn.createStatement();
			ResultSet rs = stmnt.executeQuery("select * from friends");
			while(rs.next()) {
				if(sID==rs.getInt("ID1")) {
						fSize++;
					}
				}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "No Friends");
		}

		String [] fArray= new String[fSize];
		int [] fArray2= new int [fSize];
		int j =0;
		  try {
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/dnet", "root", "");
			Statement stmnt=conn.createStatement();
			ResultSet rs = stmnt.executeQuery("select * from friends");
			while(rs.next()) {
				if(sID==rs.getInt("ID1")) {
						fArray[j]=rs.getString("name");
						fArray2[j]=rs.getInt("ID2");
						j++;
					}
				}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Input Error in ID");
		}
		 
		
		
		
		// Create an array of Button type Objects
		Button [] fButton = new Button[fArray.length];
		

		int k=10;
		// Use for loop to instantiate every button object
        for(int i = 0; i <fArray.length; ++i){
            fButton[i] = new Button(fArray[i]);
            fButton[i].addActionListener(new passV(i, sID, fArray.length, fArray2 ){
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



class passV implements ActionListener{
    int index, sID, fSize;
    int fArray2[]= new int[fSize];

    passV(int index, int ID, int fSize, int fArray2[]) {
        this.index = index;
        this.sID=ID;
        this.fSize=fSize;
        this.fArray2=fArray2;
        
    }

    public void actionPerformed(ActionEvent e) {
    	int rID= fArray2[index];
    	//System.out.print(rID);
		chats frame = new chats(sID, rID, 1);
		frame.setVisible(true);
    }
}
