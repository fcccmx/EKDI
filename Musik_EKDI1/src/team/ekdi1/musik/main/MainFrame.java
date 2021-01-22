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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;
import java.util.Map;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;

import team.ekdi1.musik.komponix.Komponix;
import team.ekdi1.musik.musikeditor.Editor;
import team.ekdi1.musik.musikexportieren.MusikExportieren;
import team.ekdi1.musik.musikexportieren.WavKomposition;
import team.ekdi1.musik.musikplayer.LadeDatei;
import team.ekdi1.musik.musikplayer.Musikspieler;
import team.ekdi1.musik.musikplayer.StatusAbfrage;


public class MainFrame extends JFrame{
	
	Scanner Status=new Scanner(System.in);
	JPanel panel1=new JPanel();
	JScrollPane jScrollPanel= new JScrollPane();
	JTextField jText_playStatus=new JTextField("PlayStatus");
	JTextField jText_takt=new JTextField();
	JLabel jL1_states=new JLabel("P. Pause");
	JLabel jL2=new JLabel("F. Fortsetzen");
	JLabel jL3=new JLabel("V. Von vorne");
	JLabel jL4=new JLabel("S. Stop");
	JLabel jL5_musikName=new JLabel();
	JLabel jL6_zeile=new JLabel();
	JLabel jL7_Taktzahl=new JLabel("Taktzahl:");
	JTable jT=new JTable();
	JTableHeader jTableHeader1=jT.getTableHeader();
	JButton jB1_openCSV=new JButton("csvFile-öffnen");
	JButton jB2_storeCSV=new JButton("csvFile-speichern");
	JButton jB3_editor=new JButton("Editor");
	JButton jB4_kompositon=new JButton("Zufällige Komposition");
	JButton jB5_export=new JButton("MP3 exportieren");
	JButton jB6_addline=new JButton("Eine Zeile hinzufügen");
	JButton jB7_deleteline=new JButton("Eine Zeile löschen");
	JButton jB8_Play=new JButton("Play");
	String[] columnNames= {"Kanal1","Kanal2","Kanal3","Kanal4"};
	String[] emptyLine= {"","","",""};
	String[][] komplettArray=new String[64][4];
	String csvFilePath;
    ArrayList<String> musikArray=new ArrayList<String>();
    String oldCellValue=null;
    String musikName=new String();
    String Statustext;
    Musikspieler mS=new Musikspieler();
    public static Map<JButton, Musikspieler> threadMap = new HashMap<JButton, Musikspieler>(16);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
				
		jB8_Play.setBounds(50, 420, 100, 30);
		jB8_Play.addActionListener(new jB8_play_Listener());
		jText_playStatus.setBounds(180,420,100,30);
		jText_playStatus.addKeyListener(new KeyListener()); 
		
		jL1_states.setBounds(180, 450, 200, 30);
		jL2.setBounds(180,470,200,30);
		jL3.setBounds(180,490,200,30);
		jL4.setBounds(180,510,200,30);
		jL5_musikName.setBounds(50, 40, 300, 50);
		jL6_zeile.setBounds(450, 40, 100, 50);
		
		jB1_openCSV.setBounds(595, 100, 150, 30);
		jB1_openCSV.addActionListener(new jButton1_openCSVFile_Listener());
		jB2_storeCSV.setBounds(595, 200, 150, 30);
		jB2_storeCSV.addActionListener(new JButton2_storeCSVFile_Listener());
		
		jB3_editor.setBounds(310, 420, 100, 30);
		jB3_editor.addActionListener(new jButton3_editor_Listener());
		jB6_addline.setBounds(310, 460, 160, 30);
		jB6_addline.addActionListener(new jB6_addline_Listener());
		jB7_deleteline.setBounds(310, 500, 160, 30);
		jB7_deleteline.addActionListener(new jB7_deleteline_Listener());
		
		jB4_kompositon.setBounds(530,460, 180, 30);
		jB4_kompositon.addActionListener(new jButton4_kompositon_Listener());
		jL7_Taktzahl.setBounds(530, 420, 80, 30);
		jText_takt.setBounds(600, 420, 80, 30);
		
		jB5_export.setBounds(595, 300, 150, 30);
		jB5_export.addActionListener(new jButton5_export_Listener());
		
		this.getContentPane().add(panel1,BorderLayout.CENTER);
		panel1.add(jText_playStatus);
		panel1.add(jText_takt);
		panel1.add(jL1_states);
		panel1.add(jL2);
		panel1.add(jL3);
		panel1.add(jL4);
		panel1.add(jL5_musikName);
		panel1.add(jL6_zeile);
		panel1.add(jL7_Taktzahl);
		panel1.add(jB1_openCSV);
		panel1.add(jB2_storeCSV);
		panel1.add(jB3_editor);
		panel1.add(jB4_kompositon);
		panel1.add(jB5_export);
		panel1.add(jB6_addline);
		panel1.add(jB7_deleteline);
		panel1.add(jB8_Play);
		
		DefaultTableModel model=new DefaultTableModel(columnNames,0);
		jT=new JTable(model);
		jScrollPanel.getViewport().add(jT);
		jScrollPanel.setBounds(50, 80, 500, 300);
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
				musikName=chooser.getSelectedFile().getName();
				jL5_musikName.setText("Musikname: "+musikName);
//				komplettArray=LadeDatei.csvRead2DArray(musikName);
				System.out.println(csvFilePath);
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
	class JButton2_storeCSVFile_Listener1 implements ActionListener{

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
	
	/**
	 * Click the editor button and jtable will be editable
	 * The modified values are returned to the arraylist
	 */
	class jButton3_editor_Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//jT.setEnabled(true);
			jT.setEnabled(true);
			jT.setBackground(Color.white);
			jT.addMouseListener(new mouseClick_Listener());
			jT.getModel().addTableModelListener(new tablecChanged_Listener());
			
		}
		
	}
	
	class jButton4_kompositon_Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			musikArray=Komponix.Randomarray(Integer.valueOf(jText_takt.getText()));
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
	
	/**
	 * Compose music and save the format as MP3 to the specified path
	 */
	class jButton5_export_Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JFileChooser chooser=new JFileChooser();
			FileNameExtensionFilter ff=new FileNameExtensionFilter("*.mp3", "mp3");
			chooser.setFileFilter(ff);
			int returnVal=chooser.showOpenDialog(jB5_export);
			if (returnVal==JFileChooser.APPROVE_OPTION) {
				File file=chooser.getSelectedFile();
				if (!file.getPath().endsWith(".mp3")) {
					file=new File(file.getPath()+".mp3");
				}
				try {
					MusikExportieren export=new MusikExportieren(musikArray,file.getPath());
					export.concatWav(musikArray);
					export.WavToMp3(file.getPath());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		}
		
	}

	class jB6_addline_Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int row=jT.getSelectedRow();
			Editor editor=new Editor(musikArray);
			DefaultTableModel model=(DefaultTableModel) jT.getModel();
			if (jT.isEnabled()==true) {
				model.insertRow(row+1, emptyLine);
				editor.AddLine(row+1);
			}
			jT.addMouseListener(new mouseClick_Listener());
			jT.getModel().addTableModelListener(new tablecChanged_Listener());
		}
		
	}
	
	class jB7_deleteline_Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int row=jT.getSelectedRow();
			if(row!=-1) {
				Editor editor=new Editor(musikArray);
				DefaultTableModel model=(DefaultTableModel) jT.getModel();
				model.removeRow(row);
				editor.DeleteLine(row);
			}
			jT.getModel().addTableModelListener(new tablecChanged_Listener());
		}
		
	}
	
	class jB8_play_Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					mS=new Musikspieler(musikArray);
//					mS.statusAbfrage.start();
					mS.MusikspielerSchleife();
					System.out.println(mS.statusAbfrage.getState());
				}
			}).start();			
		}
		
	}
	
	/**
	 * Edit on JTbale
	 * if there is an update, 
	 * change the value in the same position on the arraylist as well
	 */
	class tablecChanged_Listener implements TableModelListener{

		@Override
		public void tableChanged(TableModelEvent e) {
			// TODO Auto-generated method stub
			if (e.getType()==TableModelEvent.UPDATE) {
				String newCellValue=jT.getValueAt(e.getLastRow(), e.getColumn()).toString();
				System.out.println("new:"+newCellValue+" r:"+e.getLastRow()+" c:"+e.getColumn());
				if(!newCellValue.equals(oldCellValue)){
					musikArray.set(e.getLastRow()*4+e.getColumn(),newCellValue);
	
				}
			}
		}
		
	}
	
	/**
	 * Track the coordinates and cell value of the last mouse click on JTable
	 */
	class mouseClick_Listener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			// Record the data in the cell before 
			// entering the edit state Auto-generated method stub
			jL6_zeile.setText("Takt: "+(jT.getSelectedRow()+1));
			oldCellValue = jT.getValueAt(jT.getSelectedRow(),jT.getSelectedColumn()).toString();
			System.out.println("old:"+oldCellValue+" r:"+jT.getSelectedRow()+" c:"+jT.getSelectedColumn());
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

	
	
	class KeyListener implements java.awt.event.KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode()==KeyEvent.VK_ENTER) {
				Statustext=jText_playStatus.getText();
				mS.statusAbfrage.setStatus(Statustext);
				System.out.println(Statustext);
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	

}


