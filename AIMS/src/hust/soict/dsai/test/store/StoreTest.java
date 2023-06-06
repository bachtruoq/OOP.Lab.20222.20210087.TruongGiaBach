package hust.soict.dsai.test.store;
import hust.soict.dsai.aims.store.*;
import hust.soict.dsai.aims.disc.*;

public class StoreTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Create a new store
		Store myStore = new Store();
		
		//Create new dvd objects and add them to the cart
		DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
		myStore.addDVD(dvd1);

		DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
		myStore.addDVD(dvd2);

		DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", "Animation", 18.99f);
		myStore.addDVD(dvd3);
		
		//Remove dvd objects from the current cart
		myStore.removeDVD(dvd1);

		myStore.removeDVD(dvd3);	
	}

}
