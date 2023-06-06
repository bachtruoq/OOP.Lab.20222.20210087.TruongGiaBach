import java.util.Scanner;

class Pair {
	private double a;
	private double b;
	
	public Pair(double x, double y) {
		a = x;
		b = y;
	}
	
	public double sum() {
		return this.a + this.b;
	}
	
	public double difference() {
		return this.a - this.b;
	}
	
	public double product() {
		return this.a * this.b;
	}
	
	public String quotient() {
		if (this.b == 0) {
			return "Invalid. The divisor must be non-zero.";
		}
		else {
			return "" + (this.a/this.b) + "";
		}
	}
}

public class TwoNums {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter two numbers: ");
		String strNum1 = input.nextLine();
		String strNum2 = input.nextLine();
		double num1 = Double.parseDouble(strNum1);
		double num2 = Double.parseDouble(strNum2);
		Pair p = new Pair(num1, num2);
		
		System.out.println("Sum: " + p.sum());
		System.out.println("Difference: " + p.difference());
		System.out.println("Product: " + p.product());
		System.out.println("Quotient " + p.quotient());
	}
}
