
/* Seanna Qin
 * Programming Assignment #8, 1/30/23
 * The purpose of this program is to create a Fraction class that stores a fraction with a numerator and denominator when constructed. 
 * It also performs many different functions such as adding, subtracting, multiplying, dividing, printing, and comparing.
 * Two integers, a string improper fraction, and a whole number can be passed through, and the numerator and denominator can be called.
 * The most simple fraction will be returned.
 */

// Create Class
public class Fraction {
	// Declare private fields
	private int numerator;
	private int denominator;
	
	// Access fields
	public int getNumerator() {
		return this.numerator;
	}
	
	public int getDenominator() {
		return this.denominator;
	}
	
	// Constructor with 2 integers
	public Fraction(int num, int denom) {
		// Throw Illegal Argument if denominator is 0
		if (denom == 0) {
			throw new IllegalArgumentException("Denominator is 0");
		}
		this.numerator = num;
		this.denominator = denom;
		
		// Simplify fraction
		this.reduce();
	}
	
	// Constructor with whole number
	public Fraction(int wholeNumber) {
		this.numerator = wholeNumber;
		this.denominator = 1;
	}
	
	// Constructor with improper String
	public Fraction(String improper) {
		int i = 0;
		String num = "";
		// Get numerator from String
		while (improper.charAt(i) != '/') {
			if (improper.charAt(i) != '+' && improper.charAt(i) != '-') {
				num += improper.charAt(i);
			}
			i++;
		}
		this.numerator = Integer.parseInt(num);
		this.denominator = Integer.parseInt(improper.substring(i+1));
		
		// Check if fraction is negative
		if (improper.charAt(0) == '-') {
			this.numerator *= -1;
		}
		
		// Simplify fraction
		this.reduce();
	}
	
	// Simplify fraction method
	public void reduce() {
		// Find smaller number from numerator and denominator
		int smaller = Math.abs(this.numerator);
		if (Math.abs(this.numerator) > this.denominator) {
			smaller = this.denominator;
		}
		
		// Test for greatest common multiple
		for (int i = smaller; i >= 2; i--) {
			if (this.numerator % i == 0 && this.denominator % i == 0) {
				this.numerator /= i;
				this.denominator /= i;
			}
		}
		
		// Simplify fraction if it equals 0
		if (this.numerator == 0) {
			this.denominator = 1;
		}

	}
	
	// Add fractions
	public Fraction add(Fraction param) {
		Fraction sum = new Fraction(0);
		sum.numerator = (this.numerator*param.denominator) + (param.numerator*this.denominator);
		sum.denominator = this.denominator * param.denominator;
		sum.reduce();
		return sum;
	}
	
	// Subtract fractions
	public Fraction subtract(Fraction param) {
		Fraction diff = new Fraction(0);
		diff.numerator = (this.numerator*param.denominator) - (param.numerator*this.denominator);
		diff.denominator = this.denominator * param.denominator;
		diff.reduce();
		return diff;
	}
	
	// Multiply fractions
	public Fraction multiply(Fraction param) {
		Fraction product = new Fraction(0);
		product.numerator = this.numerator * param.numerator;
		product.denominator = this.denominator * param.denominator;
		product.reduce();
		return product;
	}
	
	// Divide fractions
	public Fraction divide(Fraction param) {
		if (param.numerator == 0) {
			throw new IllegalArgumentException("Can't divide by 0");
		}
		Fraction quotient = new Fraction(0);
		quotient.numerator = this.numerator * param.denominator;
		quotient.denominator = this.denominator * param.numerator;
		if (quotient.denominator < 0) {
			quotient.denominator *= -1;
			quotient.numerator *= -1;
		}
		quotient.reduce();
		return quotient;
	}
	
	// Override toString method
	public String toString() {
		this.reduce();
		
		if (this.denominator == 1 || this.numerator == 0) {
			return "" + this.numerator;
		}
		return this.numerator + "/" + this.denominator;
	}
	
	// Method to print mixed numbers
	public String toMixedNumberString() {
		String print = "";
		int whole = this.numerator / this.denominator;
		this.numerator = this.numerator % this.denominator;
		
		if (this.numerator == 0) {
			return "" + whole;
		}
		
		this.reduce();
		
		if (whole == 0) {
			return this.numerator + "/" + this.denominator;
		}

		print += whole + "_" + Math.abs(this.numerator) + "/" + this.denominator;
		return print;
	}
	
	// Compare fractions
	public int compareTo(Fraction param) {
		Fraction comparison = subtract(param);
		
		if (comparison.numerator < 0) {
			return -1;
		}
		else if (comparison.numerator > 0) {
			return 1;
		}
		return 0;
	}
}
