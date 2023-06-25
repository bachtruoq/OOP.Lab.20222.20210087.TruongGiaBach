package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.media.*;
import hust.soict.dsai.aims.store.*;
import hust.soict.dsai.aims.cart.*;
import hust.soict.dsai.aims.exception.PlayerException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.JFXPanel;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.*;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;

import java.awt.Window;
import java.io.IOException;
import java.util.*;
import javafx.collections.transformation.FilteredList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class CartScreenController {
	private Store store;
	private Cart cart;
	
	@FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;
    
	@FXML
	private TableView<Media> tblMedia;
	
	@FXML
	private TableColumn<Media, String> colMediaTitle;
	
	@FXML
	private TableColumn<Media, String> colMediacategory;
	
	@FXML
	private TableColumn<Media, Float> colMediaCost;
	
	public CartScreenController (Store store, Cart cart) {
		this.store = store;
		this.cart = cart;
	}
	
	@FXML
	private MenuBar menuBar;
	
	@FXML
	private Label totalcost;
	
	@FXML
	private TextField tfFilter;
	
	@FXML
	private RadioButton radioBtnFilterId;
	
	@FXML
	private RadioButton radioBtnFilterTitle;
	
	private FilteredList<Media> filteredMediaList;
	private String filterOption;
	
	@FXML
	private void initialize() {
		colMediaTitle.setCellValueFactory(
				new PropertyValueFactory<Media, String>("title"));
		colMediacategory.setCellValueFactory(
				new PropertyValueFactory<Media, String>("category"));
		colMediaCost.setCellValueFactory(
				new PropertyValueFactory<Media, Float>("cost"));
		filterOption = radioBtnFilterId.getText();
		filteredMediaList = new FilteredList<>(this.cart.getItemsOrdered(), media -> true);
		tblMedia.setItems(filteredMediaList);
		
		btnPlay.setVisible(false);
		btnRemove.setVisible(false);
		
		tblMedia.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<Media>() {
					
					@Override
					public void changed(ObservableValue<? extends Media> observable, Media oldValue,
							Media newValue) {
						if(newValue != null) {
							updateButtonBar(newValue);
						}
					}
				}
			);
		totalcost.setText(cart.totalCost()+"$");
		tfFilter.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue< ? extends String> observable, String oldValue, String newValue) {
				showFilteredMedia(newValue);
			}
		});
	}
	
	void updateButtonBar(Media media) {
		btnRemove.setVisible(true);
		if (media instanceof Playable) {
			btnPlay.setVisible(true);
		}
		else {
			btnPlay.setVisible(false);
		}
	}
	
	void showFilteredMedia(String newValue) {
		filteredMediaList.setPredicate(media -> {
	        if (newValue == null || newValue.isEmpty()) {
	            return true;
	        }
	        
	        String filterText = newValue.toLowerCase();
	        String mediaString;
	        if (filterOption.equals(radioBtnFilterId.getText())) {
		        mediaString = ("" + media.getId()).toLowerCase();
	        }
	        else {
	        	mediaString = media.getTitle().toLowerCase();
	        }

	        return mediaString.contains(filterText);
	    });
		tblMedia.setItems(filteredMediaList);
	}
	
	@FXML
	void radioBtnOption(ActionEvent event) {
		filterOption = ((RadioButton)event.getSource()).getText();
	}
	
	@FXML
	void btnRemovePressed(ActionEvent event) {
		Media media = tblMedia.getSelectionModel().getSelectedItem();
		cart.removeMedia(media);
		totalcost.setText(cart.totalCost()+"$");
	}
	
	@FXML
	void btnPlaceOrderPressed(ActionEvent event) {
		Dialog<String> order = new Dialog<>();
		order.setTitle("Place Order");
		order.setContentText("Order Successfully Placed");
		ButtonType done = new ButtonType("Ok", ButtonData.OK_DONE);
		order.getDialogPane().getButtonTypes().add(done);
		order.show();
	}
	
	@FXML
	void btnPlayPressed(ActionEvent event) throws PlayerException {
		try {
			Media media = tblMedia.getSelectionModel().getSelectedItem();
			((Playable) media).play();
			Dialog<String> mediaplay = new Dialog<>();
			mediaplay.setTitle(media.toString());
			mediaplay.setWidth(1000);
			mediaplay.setContentText("Currently Playing...");
			ButtonType done = new ButtonType("Cancel", ButtonData.OK_DONE);
			mediaplay.getDialogPane().getButtonTypes().add(done);
			mediaplay.show();			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private Window window;
	
	public void setWindow(Window window) {
		this.window = window;
	}
	
	@FXML
	void menuItemPressed(ActionEvent event) {
		MenuItem mi = (MenuItem)event.getSource();
		if (mi.getText().equals("Add Book")) {
			new AddBookToStoreScreen(store, cart);
			window.dispose();
		}
		else if (mi.getText().equals("Add CD")) {
			new AddCompactDiscToStoreScreen(store, cart);
			window.dispose();
		}
		else if (mi.getText().equals("Add DVD")) {
			new AddDigitalVideoDiscToStoreScreen(store, cart);
			window.dispose();
		}
		else if (mi.getText().equals("View Store")) {
			new StoreScreen(store, cart);
			window.dispose();
		}
		else if (mi.getText().equals("View Cart")) {
			new CartScreen(store,cart);
			window.dispose();
		}
	}
}
