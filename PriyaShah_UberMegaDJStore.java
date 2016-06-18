import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PriyaShah_UberMegaDJStore extends JFrame implements ActionListener {

	private String title;

	private DefaultTableModel inventoryModel;
	private JTable inventoryTable;
	

	// instance variables for adding a record
	private JTextField addSku;
	private JTextField addAlbum;
	private JTextField addArtist;
	private JTextField addPrice;
	private JTextField addQuantity;
	private JButton addRecordButton;

	// menu options
	private JMenuItem open;
	private JMenuItem close;
	private JMenuItem save;
	private JMenuItem exit;

	// instance variables for sellings a record
	
	private DefaultComboBoxModel sellModel; 
	private JComboBox theBox;
	
	private JTextField quantityField; 
	private JLabel currentQuantityLabel;
	private JLabel sellPriceLabel;
	private JButton removeRecord; 
	
	private ArrayList<String> temp;
	private String[] artistAndAlbumNames;
	
	
	public PriyaShah_UberMegaDJStore() {

		title = "untitled";

		setTitle("DJ Emporium --" + title);
		setSize(700, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel header = new JLabel("DJ Emporium");
		header.setBounds(300, 45, 100, 50);
		header.setForeground(Color.MAGENTA);

		// menu bar
		JMenuBar menu = new JMenuBar();
		JMenu jmFile = new JMenu("File");

		open = new JMenuItem("Open");
		close = new JMenuItem("Close");
		save = new JMenuItem("Save");
		exit = new JMenuItem("Exit");

		jmFile.add(open);
		jmFile.add(exit);
		jmFile.add(close);
		jmFile.add(save);

		menu.add(jmFile);
		setJMenuBar(menu);

		// triggers the events of the menu
		open.addActionListener(this);
		close.addActionListener(this);
		exit.addActionListener(this);
		save.addActionListener(this);

		
		PicPanel mainPanel = new PicPanel("dj.gif");
		mainPanel.setLayout(null);

		PicPanel inventoryTab = new PicPanel("inventory.jpg");

		PicPanel addRecordTab = new PicPanel("records.jpg");
		addRecordTab.setLayout(null);
		
		PicPanel sellRecordTab = new PicPanel("sell.gif");
		sellRecordTab.setLayout(null);

		// makes the tab panel
		JTabbedPane DJEmporium = new JTabbedPane();
		DJEmporium.setBounds(120, 100, 500, 300);

		// creates the table model
		String[] colNames = { "Sku", "Artist", "Album", "Price", "Quantity" };

		inventoryModel = new DefaultTableModel(colNames, 0) {

			// so the user can't edit the table
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		inventoryTable = new JTable(inventoryModel); // adds the model to the
														// table

		// don't let them reorder the columns
		inventoryTable.getTableHeader().setReorderingAllowed(false);
		inventoryTable.getColumnModel().getColumn(1).setPreferredWidth(40);

		JScrollPane inventoryScrollPanel = new JScrollPane(inventoryTable);
		inventoryScrollPanel.setBounds(45, 45, 50, 20);

		// allows the user to add a record with JLabels and JTextFields
		addSku = new JTextField();
		addSku.setBounds(75, 45, 100, 25);
		JLabel skuLabel = new JLabel("Sku");
		skuLabel.setBounds(15, 45, 100, 25);
		skuLabel.setForeground(Color.MAGENTA);

		addAlbum = new JTextField();
		addAlbum.setBounds(75, 100, 100, 25);
		JLabel albumLabel = new JLabel("Album");
		albumLabel.setBounds(15, 100, 100, 25);
		albumLabel.setForeground(Color.MAGENTA);

		addArtist = new JTextField();
		addArtist.setBounds(75, 150, 100, 25);
		JLabel artistLabel = new JLabel("Artist");
		artistLabel.setBounds(15, 150, 100, 25);
		artistLabel.setForeground(Color.MAGENTA);

		addPrice = new JTextField();
		addPrice.setBounds(300, 45, 100, 25);
		JLabel priceLabel = new JLabel("Price");
		priceLabel.setBounds(250, 45, 100, 25);
		priceLabel.setForeground(Color.MAGENTA);

		addQuantity = new JTextField();
		addQuantity.setBounds(300, 100, 100, 25);
		JLabel quantityLabel = new JLabel("Quantity");
		quantityLabel.setBounds(230, 100, 100, 25);
		quantityLabel.setForeground(Color.MAGENTA);

		addRecordButton = new JButton("Add");
		addRecordButton.setBounds(275, 150, 150, 25);

		// adds the labels and text fields to the add record picture panel
		addRecordTab.add(addSku);
		addRecordTab.add(skuLabel);
		addSku.addActionListener(this);

		addRecordTab.add(addAlbum);
		addRecordTab.add(albumLabel);
		addAlbum.addActionListener(this);

		addRecordTab.add(addArtist);
		addRecordTab.add(artistLabel);
		addArtist.addActionListener(this);

		addRecordTab.add(addPrice);
		addRecordTab.add(priceLabel);
		addPrice.addActionListener(this);

		addRecordTab.add(addQuantity);
		addRecordTab.add(quantityLabel);
		addQuantity.addActionListener(this);

		addRecordTab.add(addRecordButton);
		addRecordButton.addActionListener(this);
		
		
		//creates the sell record tab
		JLabel sellQuantityLabel = new JLabel("Quantity: ");
		sellQuantityLabel.setBounds(300, 150, 75, 75);
		
		currentQuantityLabel = new JLabel("Availible: ");
		currentQuantityLabel.setBounds(300 , 45 , 75, 75);
		
		sellPriceLabel = new JLabel("Price: $");
		sellPriceLabel.setBounds(300 , 75 , 75 , 75);
		
		removeRecord = new JButton("Sell");
		removeRecord.addActionListener(this);
		removeRecord.setBounds(350 , 200 , 50 , 25);
		
		quantityField = new JTextField();
		quantityField.setBounds(375 , 175 , 75 ,25);
		quantityField.addActionListener(this);
		
		sellRecordTab.add(sellQuantityLabel);
		sellRecordTab.add(currentQuantityLabel);
		sellRecordTab.add(sellPriceLabel);
		sellRecordTab.add(removeRecord);
		sellRecordTab.add(quantityField);

		temp = new ArrayList<String>();
		artistAndAlbumNames = new String[temp.size()];
		
		getNames();		
		
		theBox = new JComboBox(sellModel);
		theBox.addActionListener(this);
		theBox.setBounds(45, 45 , 100, 100);
		theBox.setActionCommand("sell");
		
		sellRecordTab.add(theBox);
		
		// adds the table to the inventory picture panel
		inventoryTab.add(inventoryScrollPanel);

		// adds the picture panel to the tab panel
		DJEmporium.add("Inventory", inventoryTab);

		// adds the adding record tab
		DJEmporium.add("Add a Record", addRecordTab);

		//adds the sell record tab to the tab panel
		DJEmporium.add("Sell Record" , sellRecordTab);
		
		
		// adds the tab pane to the DJ picture panel
		mainPanel.add(DJEmporium);
		mainPanel.add(header);

		// adds the main panel to the screen
		add(mainPanel);

		setVisible(true);
	}

	// Picture Panel inner class
	class PicPanel extends JPanel {

		private BufferedImage image;
		private int w, h;

		public PicPanel(String fname) {

			// reads the image
			try {
				image = ImageIO.read(new File(fname));
				w = image.getWidth();
				h = image.getHeight();

			} catch (IOException ioe) {
				System.out.println("Could not read in the pic");
				System.exit(0);
			}

		}

		public Dimension getPreferredSize() {
			return new Dimension(w, h);
		}

		// this will draw the image
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, this);
		}
	}

	public void actionPerformed(ActionEvent ae) {

		// if the add record button is pressed
		if (ae.getActionCommand().equals("Add")) {

			if (checkAddRecord()) {

				Object[] row = { addSku.getText(), addAlbum.getText(),
						addArtist.getText(), addPrice.getText(),
						addQuantity.getText() };
				inventoryModel.addRow(row);

				JOptionPane.showMessageDialog(this, "This record was added",
						"Record Added", JOptionPane.DEFAULT_OPTION);

				clearValues();

				// changes the title
				title = "*" + title;
				setTitle("DJ Emporium --" + title);
				
				//updates the sell record tab
				getNames();
			}
		}
		// if the user clicks on the menu bar
		
		//opens a file
		if (ae.getActionCommand().equals("Open")) {

			openFile();
			
			//updates the sell record tab
			getNames();
		}

		//saves a file
		if (ae.getActionCommand().equals("Save")){
			
			saveFile();
		}
		
		//exits out of the program but asks if the user if they want to save the file first
		if(ae.getActionCommand().equals("Exit")){
			
			int toSave = JOptionPane.YES_NO_OPTION;

			JOptionPane.showConfirmDialog(null, "Do you want to exit the program?", "WARNING", toSave);

			if (toSave == JOptionPane.YES_OPTION) {
				
				saveFile();
			}
			
			if (toSave == JOptionPane.NO_OPTION) {
				 
				System.exit(0);
			}
              
		}
		
		//asks the user if they want to close the current DJ emporium open 
		if(ae.getActionCommand().equals("Close")){
			
			int toClose = JOptionPane.YES_NO_OPTION;

			JOptionPane.showConfirmDialog(null, "Do you want to close the current document?", "WARNING", toClose);

			if (toClose == JOptionPane.YES_OPTION) {
				
				saveFile();
			}

			if (toClose == JOptionPane.NO_OPTION) {
				
				clearValues();
				
				for (int r = 0; r < inventoryModel.getRowCount(); r++) {

					for (int c = 0; c < inventoryModel.getColumnCount(); c++) {
						
						inventoryModel.setValueAt(null, r , c);
					}
				}
				
			}
		}	
			
		if(ae.getActionCommand().equals("sell")){
			
			int selected = theBox.getSelectedIndex();
			
			if(selected != -1){
				
				currentQuantityLabel.setText("Quantity:" + inventoryModel.getValueAt(selected,4));
				sellPriceLabel.setText("Price: $" + inventoryModel.getValueAt(selected,3));
			}
		}
              
		if(ae.getActionCommand().equals("Sell")){
			
			int selected = theBox.getSelectedIndex();
			
			if(selected != -1){
				
				if(checkQuantity(selected)){
					
					int quantity = Integer.parseInt(quantityField.getText());
					inventoryModel.setValueAt((int)inventoryModel.getValueAt(selected , 4) - quantity , selected , 4);
					
					int owe = (int)inventoryModel.getValueAt(3, selected) * quantity; 
					
					 DecimalFormat df = new DecimalFormat("#.##");
					
					JOptionPane.showMessageDialog(this, "You owe $" + df.format(owe), "Record Sold", JOptionPane.INFORMATION_MESSAGE);
				}
				
				else{
					
					JOptionPane.showMessageDialog(this, "You did not select a record/enter a quantity correctly", "Record not sold", JOptionPane.ERROR_MESSAGE);

				}
			}
			
	}
	}

	// checks all the fields before a new record is added
	private boolean checkAddRecord() {

		String skuString = addSku.getText();
		String artist = addArtist.getText();
		String album = addAlbum.getText();
		String priceString = addPrice.getText();
		String quantityString = addQuantity.getText();

		double price = 0;
		int quantity = 0;
		int sku = 0;

		JOptionPane pane = new JOptionPane();

		// checks if any of the fields are empty
		if (skuString.equals("") || artist.equals("") || album.equals("")
				|| priceString.equals("") || quantityString.equals("")) {

			JOptionPane.showMessageDialog(this,
					"One or more fields were left blank.", "Cannot Add Record",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		// makes sure the quantity and price and sku are actual numbers

		try {

			price = Double.parseDouble(priceString);
			quantity = Integer.parseInt(quantityString);
			sku = Integer.parseInt(skuString);

		} catch (NumberFormatException e) {

			JOptionPane.showMessageDialog(this,
					"The price, quantity, and sku must be numbers over 0",
					"Cannot Add Record", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (price < 0 || quantity < 0) {

			JOptionPane.showMessageDialog(this,
					"The price and quantity must be positive numbers",
					"Cannot Add Record", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		// scans the tables to see if the table already exists

		int numRows = inventoryModel.getRowCount();

		for (int r = 0; r < numRows; r++) {

			if (skuString.equals(inventoryModel.getValueAt(r, 0))) {

				int newQuantity = (int) inventoryModel.getValueAt(r, 4)
						+ quantity;
				inventoryModel.setValueAt(newQuantity, r, 4);

				JOptionPane
						.showMessageDialog(
								this,
								"This record already is in inventory. The quantity has been increased.",
								"Record Added", JOptionPane.PLAIN_MESSAGE);

				clearValues();

				return false;
			}
		}

		return true;

	}

	// clears all the text fields
	private void clearValues() {

		addSku.setText("");
		addAlbum.setText("");
		addArtist.setText("");
		addPrice.setText("");
		addQuantity.setText("");
	}

	private void openFile() {

		try {
			UIManager.LookAndFeelInfo[] laf = UIManager
					.getInstalledLookAndFeels();

			for (UIManager.LookAndFeelInfo g : laf) {
				System.out.println(g.toString());
			}
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch (Exception e) {
			System.out.println("Problems editing the look and feel");
			System.exit(-1);
		}

		JFileChooser jfc = new JFileChooser();

		// pop up the file menu box, "null" means we don't want to associate it
		// with a component

		// if you want a Save Box to show instead
		// call the method showSaveDialog....
		int result = jfc.showOpenDialog(null);

		// the box will stay open until the user hits ok, cancel, x....

		// if the user selectes "Ok", extract the file

		File f = null;
		Scanner sc = null;
		if (result == JFileChooser.APPROVE_OPTION) {
			f = jfc.getSelectedFile();

			// read and display the data from the file

			try {
				sc = new Scanner(f);
			} catch (FileNotFoundException e) {
				System.out.println("File Not Found!");
				System.exit(-1);
			}

			// changes the title
			title = f.getName();
			setTitle("DJ Emporium --" + title);

			while (sc.hasNextLine()) {

				int sku = sc.nextInt();
				String artist = sc.nextLine();
				String album = sc.nextLine();

				sc.nextLine(); // ignore scenario

				double price = sc.nextDouble();
				int quantity = sc.nextInt();

				Object[] row = { sku, artist, album, price, quantity };
				inventoryModel.addRow(row);
			}
		}
	}

	private void saveFile() {

		try {
			UIManager.LookAndFeelInfo[] laf = UIManager
					.getInstalledLookAndFeels();

			for (UIManager.LookAndFeelInfo g : laf) {
				System.out.println(g.toString());
			}
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch (Exception e) {
			System.out.println("Problems editing the look and feel");
			System.exit(-1);
		}

		JFileChooser jfc = new JFileChooser();

		// pop up the file menu box, "null" means we don't want to associate it
		// with a component

		// if you want a Save Box to show instead
		// call the method showSaveDialog....
		int result = jfc.showSaveDialog(null);

		// the box will stay open until the user hits ok, cancel, x....

		// if the user selectes "Ok", extract the file

		File f = null;
		FileWriter outfile = null;
		if (result == JFileChooser.APPROVE_OPTION) {
			f = jfc.getSelectedFile();

			// outputs the data from inventory to a file
			try {
				outfile = new FileWriter(f);

				for (int r = 0; r < inventoryModel.getRowCount(); r++) {

					for (int c = 0; c < inventoryModel.getColumnCount(); c++) {

						outfile.write(inventoryModel.getValueAt(r, c) + "\n");
					}
				}

				outfile.close();

			} catch (IOException e) {

				System.out.println("IO Exception");
				System.exit(-1);
			}

			// changes the title
			title = f.getName();
			setTitle("DJ Emporium --" + title);

		}

	}
	
	// gets the album and artist names
	private void getNames() {

		for (int i = 0; i < inventoryModel.getRowCount(); i++) {

			temp.add(inventoryModel.getValueAt(i, 1) + "--" + inventoryModel.getValueAt(i, 2));

		}


		for (int i = 0; i < artistAndAlbumNames.length; i++) {

			artistAndAlbumNames[i] = temp.get(i);
		}
		
		sellModel = new DefaultComboBoxModel(artistAndAlbumNames);
	}
	
	private boolean checkQuantity(int max){
		
		int quantity = 0;
		
		try {

			 quantity = Integer.parseInt(quantityField.getText());


		} catch (NumberFormatException e) {

			JOptionPane.showMessageDialog(this,
					"The quantity has to be a number",
					"Cannot Sell Record", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if(quantity < 0 && quantity > (int)inventoryModel.getValueAt(max,4)){
			
			JOptionPane.showMessageDialog(this,
					"The quantity has to be a positive number less than the amount in store",
					"Cannot Sell Record", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}

	public static void main(String[] args) {

		PriyaShah_UberMegaDJStore dj = new PriyaShah_UberMegaDJStore();

	}

}
