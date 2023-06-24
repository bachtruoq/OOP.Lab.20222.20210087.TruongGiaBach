package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.store.*;
import hust.soict.dsai.aims.media.*;
import hust.soict.dsai.aims.cart.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;

public class StoreScreen extends JFrame{
	private Store store;
	private Cart cart = new Cart();    
    
	JPanel createNorth() {
		JPanel north = new JPanel();
		north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
		north.add(createMenuBar());
		north.add(createHeader());
		return north;
	}
	
	JMenuBar createMenuBar() {
		Store store = this.store;
		Cart cart = this.cart;
		
		JMenu menu = new JMenu("Options");
		
		JMenu smUpdateStore = new JMenu("Update Store");
		JMenuItem addBookScreen = new JMenuItem("Add Book");
		addBookScreen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AddBookToStoreScreen(store, cart);
				dispose();
			}			
		});
		smUpdateStore.add(addBookScreen);
		JMenuItem addCDScreen = new JMenuItem("Add CD");
		addCDScreen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AddCompactDiscToStoreScreen(store, cart);
				dispose();
			}			
		});
		smUpdateStore.add(addCDScreen);
		JMenuItem addDVDScreen = new JMenuItem("Add DVD");
		addDVDScreen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AddDigitalVideoDiscToStoreScreen(store, cart);
				dispose();
			}			
		});
		smUpdateStore.add(addDVDScreen);
		
		menu.add(smUpdateStore);
		JMenuItem viewStoreScreen = new JMenuItem("View Store");
		viewStoreScreen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new StoreScreen(store, cart);
				dispose();
			}			
		});
		menu.add(viewStoreScreen);
		JMenuItem viewCartScreen = new JMenuItem("View Cart");
		viewCartScreen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new CartScreen(store, cart);
				dispose();
			}			
		});
		menu.add(viewCartScreen);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		menuBar.add(menu);
		
		return menuBar;
	}
	
	JPanel createHeader() {
		
		JPanel header = new JPanel();
		header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
		
		JLabel title = new JLabel("AIMS");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
		title.setForeground(Color.CYAN);
		
		JButton viewcart = new JButton("View cart");
		viewcart.setPreferredSize(new Dimension(100, 50));
		viewcart.setMaximumSize(new Dimension(100, 50));
		viewcart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CartScreen(store, cart);
				dispose();				
			}			
		});
		
		header.add(Box.createRigidArea(new Dimension(10, 10)));
		header.add(title);
		header.add(Box.createHorizontalGlue());
		header.add(viewcart);
		header.add(Box.createRigidArea(new Dimension(10, 10)));
		
		return header;
	}
	
	JPanel createCenter() {
		
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(3, 3, 2, 2));
		
		ArrayList<Media> mediaInStore = store.getItemsInStore();
		for (int i = 0; i < 9; i++) {
			MediaStore cell = new MediaStore(mediaInStore.get(i));
			cell.getAddtocart().addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					cart.addMedia(cell.getMedia());
				}				
			});
			center.add(cell);
		}
		
		return center;
	}
	
	public StoreScreen(Store store) {
		this.store = store;
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		cp.add(createNorth(), BorderLayout.NORTH);
		cp.add(createCenter(), BorderLayout.CENTER);
		
		setVisible(true);
		setTitle("Store");
		setSize(1024, 768);
	}
	
	public StoreScreen(Store store, Cart cart) {
		this.store = store;
		this.cart = cart;
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		cp.add(createNorth(), BorderLayout.NORTH);
		cp.add(createCenter(), BorderLayout.CENTER);
		
		setVisible(true);
		setTitle("Store");
		setSize(1024, 768);
		
	}
}
