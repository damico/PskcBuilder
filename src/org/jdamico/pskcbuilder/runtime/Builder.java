package org.jdamico.pskcbuilder.runtime;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jdamico.pskcbuilder.components.Controller;
import org.jdamico.pskcbuilder.utils.Commons;
import org.jdamico.pskcbuilder.utils.Constants;

public class Builder implements ActionListener, PropertyChangeListener { 
	JPanel cards; 

	final static String BUTTONPANEL = "Buttons";

	JButton inputButton;
	JButton outputButton;

	JButton executeButton;
	JFileChooser fc = new JFileChooser();
	JFileChooser dc = new JFileChooser();


	String inputButtonLabel = "input";
	String outputButtonLabel = "output";

	String input = null, output = null;
	JCheckBox typeProcA = null;


	private Task task;
	private JProgressBar progressBar;
	boolean done = false;

	JTextArea console;
	JTextField separator = null;
	JLabel manufacturerLabel = null;
	JLabel separatorLabel = null;
	JTextField manufacturer = null;

	JLabel respLenLabel = null;
	JTextField respLen = null;

	JLabel counterLabel = null;
	JTextField counter = null;

	JLabel intervalLabel = null;
	JTextField interval = null;

	public void addComponentToPane(Container pane) {

		dc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		separatorLabel = new JLabel("Column sepator ");

		respLenLabel = new JLabel("Resp. Length ");
		respLenLabel.setBounds(4, 186, 100, 26);
		respLen = new JTextField();
		respLen.setText("6");
		respLen.setBounds(4+88, 186, 16, 26);

		manufacturerLabel = new JLabel("Manufacturer ");
		manufacturer = new JTextField();
		manufacturer.setText("");
		manufacturerLabel.setBounds(282, 152, 88, 26);
		manufacturer.setBounds(282+88, 152, 128, 26);

		counterLabel = new JLabel("Counter ");
		counter = new JTextField();
		counter.setText("0");
		counterLabel.setBounds(4+88+18, 186, 88, 26);
		counter.setBounds(4+88+70, 186, 16, 26);

		intervalLabel = new JLabel("Interval ");
		interval = new JTextField();
		interval.setText("30");
		intervalLabel.setBounds(4+178, 186, 88, 26);
		interval.setBounds(4+88+140, 186, 20, 26);

		separator = new JTextField();
		separator.setText(";");

		progressBar = new JProgressBar(0, 100);
		progressBar.setString("ready.");
		progressBar.setStringPainted(true);

		inputButton = new JButton(inputButtonLabel);
		inputButton.addActionListener(this);
		inputButton.setBounds(4, 152, 70, 30);

		outputButton = new JButton(outputButtonLabel);
		outputButton.addActionListener(this);
		outputButton.setBounds(76, 152, 80, 30);

		executeButton  = new JButton("Build!");
		executeButton.addActionListener(this);

		console = new JTextArea();
		console.setBackground(Color.BLACK);
		console.setForeground(Color.GREEN);

		console.setBounds(4,154+30+8,494, 130);
		console.setEditable(true);
		console.setEnabled(true);
		console.setAutoscrolls(true);

		separatorLabel.setBounds(160, 152, 120, 26);
		separator.setBounds(262, 152, 18, 26);


		executeButton.setBounds(255, 184, 70, 26);
		progressBar.setBounds(330, 184, 168, 26);
		JScrollPane sp = new JScrollPane(console);
		sp.setBounds(5,154+60+2,494, 155);

		BufferedImage logoTitle = null;
		try {
			logoTitle = ImageIO.read(new File("./resources/logoPskc.png"));
			JLabel logoTitleLabel = new JLabel(new ImageIcon( logoTitle ));
			logoTitleLabel.setBounds(0, 0, 498, 149);
			pane.add(logoTitleLabel, BorderLayout.PAGE_START);
		} catch (IOException e) {
			String msg = "Wrong installation. The executable file must be parallel to resources/ directory. The application will try to run anyway.";
			JOptionPane.showMessageDialog(new JFrame(),msg,"Error",JOptionPane.ERROR_MESSAGE);
		}
		pane.setLayout(null);
		pane.add(inputButton);
		pane.add(outputButton);
		pane.add(separatorLabel);
		pane.add(separator);

		pane.add(manufacturerLabel);
		pane.add(manufacturer);

		pane.add(respLen);
		pane.add(respLenLabel);

		pane.add(counter);
		pane.add(counterLabel);

		pane.add(interval);
		pane.add(intervalLabel);

		pane.add(executeButton);
		pane.add(progressBar);

		pane.add(sp);

	}

	public void itemStateChanged(ItemEvent evt) {
		CardLayout cl = (CardLayout)(cards.getLayout());
		cl.show(cards, (String)evt.getItem());
	}

	/**
	 * Create the GUI and show it.  For thread safety,
	 * this method should be invoked from the
	 * event dispatch thread.
	 */
	private static void createAndShowGUI() {

		JFrame frame = new JFrame(Constants.APP_NAME+" - "+Constants.APP_VERSION);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Builder gui = new Builder();
		gui.addComponentToPane(frame.getContentPane());

		ImageIcon icon = new ImageIcon("./resources/logo.png");
		frame.setIconImage(icon.getImage());

		frame.pack(); 
		frame.setSize(510, 400);
		frame.setResizable(false);
		frame.setVisible(true);

	}

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}

		UIManager.put("swing.boldMetal", Boolean.FALSE);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		console.setForeground(Color.GREEN);
		if(e.getSource() == inputButton){
			int returnVal = fc.showOpenDialog(cards);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				input = file.getAbsolutePath();
				console.append("Input: "+input+"\n");
			}

		}else if(e.getSource() == executeButton){

			console.setText("");

			/**
			 * Check for:
			 * 1 input (path file)
			 * 2 output (path dir)
			 * 3 colsep (not null)
			 * 4 manufact (not null)
			 * 5 counter (int)
			 * 6 resplen (int 4/6/8)
			 * 7 interval (int 30/60)
			 */

			boolean valInput = false;
			if(input!=null) valInput = true;
			boolean valOutput = false;
			if(output!=null) valOutput = true;
			boolean valColsep = false;
			if(separator.getText()!=null) valColsep = true;
			boolean valManu = false;
			if(manufacturer.getText()!=null && manufacturer.getText().length()>1) valManu = true;
			boolean valCounter = false;
			if(counter.getText()!=null){
				valCounter = true;
				try{
					Integer.valueOf(counter.getText());
				}catch (Exception nfe) {
					valCounter = false;
				}
			}

			boolean valInterval = false;
			if(interval.getText()!=null){
				valInterval = true;
				try{
					Integer.valueOf(interval.getText());
				}catch (Exception nfe) {
					valInterval = false;
				}
			}

			boolean valLen = false;
			if(respLen.getText()!=null){
				valLen = true;
				try{
					Integer.valueOf(respLen.getText());
				}catch (Exception nfe) {
					valLen = false;
				}
			}

			if(!valInput) 		console.append("Invalid input file path!\n");
			if(!valOutput) 		console.append("Invalid output file path!\n");
			if(!valColsep) 		console.append("Invalid column separator!\n");
			if(!valManu) 		console.append("Invalid manufacturer name!\n");
			if(!valCounter) 	console.append("Invalid counter!\n");
			if(!valInterval) 	console.append("Invalid interval!\n");
			if(!valLen) 		console.append("Invalid response length!\n");

			if(valInput && valOutput && valColsep && valManu && valCounter && valInterval && valLen){
				setControls(done);
				task = new Task();
				task.addPropertyChangeListener(this);
				task.execute();
			}

		}else if(e.getSource() == outputButton){

			int returnVal = dc.showOpenDialog(cards);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = dc.getSelectedFile();
				output = file.getPath();
				console.append("Output: "+output+"\n");
			}
		}


	}

	class Task extends SwingWorker<Void, Void> {

		@Override
		public Void doInBackground() {


			while (!done) {

				if(input!=null){

					try {

						int tokensImported = Controller.getInstance().buildPskc(input, output, manufacturer.getText(),separator.getText(), respLen.getText(), counter.getText(), interval.getText());

						console.append("Tokens imported: "+tokensImported+"\n");
						console.append("==============================\n");
						console.append(Commons.getInstance().getStringFromFile(output+Constants.DEFAULT_OUTPUT_FILENAME));
						
						progressBar.setString("done.");		
						done = true;
						setControls(done);
					} catch (Exception e) {
						console.setForeground(Color.RED);
						console.setText("Pskc build failed: ("+e.getMessage()+")");						
						progressBar.setString("error.");
						done = true;
						setControls(done);
						e.printStackTrace();
					}

				}else{
					console.setForeground(Color.RED);
					console.setText("Invalid parameters.");					
					progressBar.setString("error.");
					done = true;

					setControls(done);
				}

			}
			return null;
		}

		/*
		 * Executed in event dispatching thread
		 */
		@Override
		public void done() {
			Toolkit.getDefaultToolkit().beep();
			done = true;
			setControls(done);
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		if (!done){
			progressBar.setString("running...");
			progressBar.setIndeterminate(true);
		} else {
			progressBar.setIndeterminate(false);
			done = false;
		}
	}

	public void setControls(boolean enable){


		// buttons
		inputButton.setEnabled(enable);
		outputButton.setEnabled(enable);
		executeButton.setEnabled(enable);
		// fields
		separator.setEnabled(enable);
		counter.setEnabled(enable);
		interval.setEnabled(enable);
		manufacturer.setEnabled(enable);
		respLen.setEnabled(enable);




	}
}
