
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
}
