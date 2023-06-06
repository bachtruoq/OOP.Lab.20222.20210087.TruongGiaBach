package hust.soict.dsai.aims.store;
import java.util.ArrayList;
import hust.soict.dsai.aims.disc.*;

public class Store {
	private ArrayList<DigitalVideoDisc> itemsInStore = new ArrayList<DigitalVideoDisc>();
	
	public void addDVD(DigitalVideoDisc disc) {
		itemsInStore.add(disc);
		System.out.println("The disc has been added successfully.");
	}
	
	public void removeDVD(DigitalVideoDisc disc) {
		for (DigitalVideoDisc d: itemsInStore) {
			if (d == disc) {
				itemsInStore.remove(disc);
				System.out.println("The disc has been removed successfully.");
				return;
			}
		}
		System.out.println("No such disc.");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub		
	}

}
