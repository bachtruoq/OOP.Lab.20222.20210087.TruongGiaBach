package hust.soict.dsai.aims.cart;
import hust.soict.dsai.aims.disc.*;

public class Cart {
	public static final int MAX_NUMBERS_ORDERED = 20;
	private DigitalVideoDisc itemsOrdered[] = new DigitalVideoDisc[MAX_NUMBERS_ORDERED];
	private int qtyOrdered;
	
	public void addDigitalVideoDisc(DigitalVideoDisc disc) {
		if (this.qtyOrdered < MAX_NUMBERS_ORDERED) {
			itemsOrdered[this.qtyOrdered++] = disc;
			System.out.println("The disc has been added");
		}
		else {
			System.out.println("Failed. The cart is full");
		}
	}
	
	public void addDigitalVideoDisc(DigitalVideoDisc[] dvdList) {
		if (qtyOrdered + dvdList.length > MAX_NUMBERS_ORDERED) {
			System.out.println("Failed. Cart's maximum length reached.");
		}
		else {
			for (DigitalVideoDisc d: dvdList) {
				itemsOrdered[this.qtyOrdered++] = d;
			}
			System.out.println("The discs have been added.");
		}
	}
	
	public void addDigitalVideoDisc(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2) {
		if (qtyOrdered + 2 > MAX_NUMBERS_ORDERED) {
			System.out.println("Failed. Cart's maximum length reached.");
		}
		else {
			itemsOrdered[this.qtyOrdered++] = dvd1;
			itemsOrdered[this.qtyOrdered++] = dvd2;
			System.out.println("The discs has been added");			
		}
	}
	
	public void removeDigitalVideoDisc(DigitalVideoDisc disc) {
		for (int i = 0; i < this.qtyOrdered; i++) {
			if (disc == this.itemsOrdered[i]) {
				for (int j = i; j < this.qtyOrdered - 1; j++) {
					this.itemsOrdered[j] = this.itemsOrdered[j+1];
				}
				this.itemsOrdered[--this.qtyOrdered] = null;
				
				System.out.println("The disc has been removed");
				return;
			}
		}
		System.out.println("Failed. The disc can't be found.");
	}
	
	public double totalCost() {
		double ret = 0;
		for (int i = 0; i < this.qtyOrdered; i++) {
			ret += this.itemsOrdered[i].getCost();
		}
		return ret;
	}
	
	public void print() {
		System.out.println("***********************CART***********************\n" + "Ordered Items:");
		for (int i = 0; i < this.qtyOrdered; i++) {
			System.out.println(String.format("%d. %s", i+1, this.itemsOrdered[i].getDetail()));
		}
		System.out.println(String.format("Total cost: %.2f\n***************************************************",
										 this.totalCost()));
	}
	
	public boolean search(int ID) {
		for (DigitalVideoDisc d: this.itemsOrdered) {
			if (d == null) {break;}
			if (d.getid() ==  ID) {
				System.out.println(d.getDetail());
				return true;
			}
		}
		System.out.println("No match is found.");
		return false;		
	}
	
	public boolean search(String title) {
		for (DigitalVideoDisc d: this.itemsOrdered) {
			if (d == null) {break;}
			if (d.getTitle() ==  title) {
				System.out.println(d.getDetail());
				return true;
			}
		}
		System.out.println("No match is found.");
		return false;
	}
}
