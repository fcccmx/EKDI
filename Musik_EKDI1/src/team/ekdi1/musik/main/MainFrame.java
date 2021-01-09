package team.ekdi1.musik.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import team.ekdi1.musik.musikplayer.LadeDatei;


public class MainFrame extends JFrame{
	
	Scanner Status=new Scanner(System.in);
	JPanel panel1=new JPanel();
	JScrollPane jScrollPanel= new JScrollPane();
	JTextField playStatus=new JTextField();
	JLabel jL1_states=new JLabel();
	JTable jT=new JTable();
	JTableHeader jTableHeader1=jT.getTableHeader();
	JButton jB1_openCSV=new JButton("csvFile-oeffnen");
	JButton jB2_storeCSV=new JButton("csvFile-speichern");
	JButton jB3_editor=new JButton("Editor");
	JButton jB4_kompositon=new JButton("kompositon");
	JButton jB5_export=new JButton("MP3 exportieren");
	String[] columnNames= {"Kanal1","Kanal2","Kanal3","Kanal4"};
	String csvFilePath;
    ArrayList<String> musikArray;
//    Vector vcolumnNames=new Vector();
//    Vector vData=new Vector();

    

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MainFrame() {
		setTitle("MusikSpieler");
        setResizable(false);
        this.setBounds(50,50,800, 600);
		panel1.setLayout(null);
		
		playStatus.setBounds(50,440,100,30);
		jL1_states.setBounds(50, 470, 200, 30);
		
		jB1_openCSV.setBounds(600, 110, 150, 30);
		jB1_openCSV.addActionListener(new jButton1_openCSVFile_Listener());
		
		jB2_storeCSV.setBounds(600, 180, 150, 30);
		jB2_storeCSV.addActionListener(new JButton2_storeCSVFile_Listener());
		
		jB3_editor.setBounds(180, 440, 100, 30);
		jB3_editor.addActionListener(new jButton3_editor_Listener());
		
		jB4_kompositon.setBounds(310,440, 100, 30);
		
		jB5_export.setBounds(430, 440, 100, 30);
		
		this.getContentPane().add(panel1,BorderLayout.CENTER);
		panel1.add(playStatus);
		panel1.add(jL1_states);
		panel1.add(jB1_openCSV);
		panel1.add(jB2_storeCSV);
		panel1.add(jB3_editor);
		panel1.add(jB4_kompositon);
		panel1.add(jB5_export);
		
//		vcolumnNames.add("Kanal1");
//		vcolumnNames.add("Kanal2");
//		vcolumnNames.add("Kanal3");
//		vcolumnNames.add("Kanal4");
		DefaultTableModel model=new DefaultTableModel(columnNames,0);
		jT=new JTable(model);
//		jT.addMouseListener();
		jScrollPanel.getViewport().add(jT);
		jScrollPanel.setBounds(30, 100, 500, 300);
		panel1.add(jScrollPanel);
		
			
	}
	
	/**
	 * Select the csv file from the system
	 * get the path to the csv file
	 * save the contents of the csv file to an arraylist
	 */
	class jButton1_openCSVFile_Listener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JFileChooser chooser=new JFileChooser();
			FileNameExtensionFilter ff=new FileNameExtensionFilter("*.csv", "csv");
			chooser.setFileFilter(ff);
			int returnVal=chooser.showOpenDialog(jB1_openCSV);
			if (returnVal==JFileChooser.APPROVE_OPTION) {
				csvFilePath=chooser.getSelectedFile().getAbsolutePath();
//				String path=chooser.getSelectedFile().getName();
//				System.out.println(csvFilePath);
				musikArray=LadeDatei.csvRead(csvFilePath);
//				System.out.println(musikArray);
				String[][] arrayData=new String[musikArray.size()/4][4];
				for (int i = 0; i < musikArray.size(); i++) {
					arrayData[i/4][i%4]=musikArray.get(i);
				}
				DefaultTableModel model=new DefaultTableModel(arrayData,columnNames);
				jT=new JTable(model);
				jT.setEnabled(false);
				jT.setBackground(new Color(228, 228,228));
				jScrollPanel.getViewport().add(jT);
			}
		}
	}
	
	/**
	 * Save arraylist as csv file to the selected file path
	 * If the file path does not exist, create a new file
	 */
	class JButton2_storeCSVFile_Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JFileChooser chooser=new JFileChooser();
			FileNameExtensionFilter ff=new FileNameExtensionFilter("*.csv", "csv");
			chooser.setFileFilter(ff);
			int returnVal=chooser.showOpenDialog(jB1_openCSV);
			if (returnVal==JFileChooser.APPROVE_OPTION) {
				File file=chooser.getSelectedFile();
				if (!file.getPath().endsWith(".csv")) {
					file=new File(file.getPath()+".csv");
				}
				LadeDatei.csvWrite(musikArray,file.getPath());
			}
		}
		
	}
	
	
	class jButton3_editor_Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//jT.setEnabled(true);
			jT.setEnabled(true);
			jT.setBackground(Color.white);
			
			
		}
		
	}
	
  
	
}


