package hr.fer.zemris.java.tecaj.hw2;

import java.util.Locale;

/**
 * This class offers one basic constructor for creating complex numbers in the
 * form of <code>realPart + imaginaryPart</code> and various operations for
 * complex number manipulation. Every complex number can be transformed from
 * magnitude and angle form (exponential or polar form) to number form.
 *
 * @author Mario Bobic
 */
public class ComplexNumber {
	
	/** Lowest decimal number value until it is regarded as zero. */
	private static final double ZERO_LIMIT = 10E-20;

	/** Real part of the complex number. */
	private double real;
	/** Imaginary part of the complex number. */
	private double imag;
	
	/**
	 * Constructs a new instance of a ComplexNumber, with the given parameters.
	 * 
	 * @param real real part of the complex number
	 * @param imag imaginary part of the complex number
	 */
	public ComplexNumber(double real, double imag) {
		this.real = real;
		this.imag = imag;
	}
	
	/**
	 * Returns a new instance of a ComplexNumber created from the given
	 * <code>real</code> part of the complex number. The returned complex number
	 * will have the imaginary part set to 0.0i.
	 * 
	 * @param real real part to be set for this complex number
	 * @return a new instance of a complex number with the given parameter
	 */
	public static ComplexNumber fromReal(double real) {
		return new ComplexNumber(real, 0.0);
	}
	
	/**
	 * Returns a new instance of a ComplexNumber created from the given
	 * <code>imaginary</code> part of the complex number. The returned complex
	 * number will have the real part set to 0.0.
	 * 
	 * @param imaginary imaginary part to be set for this complex number
	 * @return a new instance of a complex number with the given parameter
	 */
	public static ComplexNumber fromImaginary(double imaginary) {
		return new ComplexNumber(0.0, imaginary);
	}
	
	/**
	 * Returns a new instance of a ComplexNumber created from the given
	 * <code>magnitude</code> and <code>angle</code> of a polar form.
	 * 
	 * @param magnitude magnitude of the polar form
	 * @param angle angle of the polar form
	 * @return a new instance of a complex number with the given parameters
	 */
	public static ComplexNumber fromMagnitudeAndAngle(double magnitude, double angle) {
		if (magnitude < 0) {
			throw new IllegalArgumentException("Magnitude must not be negative.");
		}
		
		return new ComplexNumber(magnitude*Math.cos(angle), magnitude*Math.sin(angle));
	}
	
	/**
	 * Parses the given string into a complex number and returns it. The string
	 * is expected to be in an adequate format for parsing. Here are some
	 * examples of the expected string:<br>
	 * <code>"3.51", "-3.17", "-2.71i", "i", "1", "-2.71-3.15i", "2 + 3i"</code>
	 * <br>
	 * The given string will not be parsed if the format of the complex number
	 * has swapped real and imaginary part places. Instead, a
	 * {@linkplain NumberFormatException} will be thrown. In case of an empty
	 * string or <code>null</code>, an {@linkplain IllegalArgumentException}
	 * will be thrown.
	 * 
	 * @param s string to be parsed
	 * @return an instance of ComplexNumber
	 * @throws IllegalArgumentException if the given string is null or empty
	 * @throws NumberFormatException if the string cannot be parsed
	 */
	public static ComplexNumber parse(String s) {
		if (s == null || s.trim().isEmpty()) {
			throw new IllegalArgumentException("Cannot parse empty string.");
		}
		
		/* No need for whitespaces. */
		s = s.replace(" ", "");
		
		/* Check if it's only i. */
		if (s.equals("i") || s.equals("+i")) {
			return new ComplexNumber(0.0, 1.0);
		} else if (s.equals("-i")) {
			return new ComplexNumber(0.0, -1.0);
		}
		
		/* Locate the beginning of a second number, if any. */
		int delimiter = Math.max(s.indexOf('+', 1), s.indexOf('-', 1));
		
		if (delimiter == -1) {
			if (s.endsWith("i")) {
				double imag = Double.parseDouble(s.replace("i", ""));
				return new ComplexNumber(0.0, imag);
			} else {
				double real = Double.parseDouble(s);
				return new ComplexNumber(real, 0.0);
			}
		} else {
			if (!s.contains("i")) {
				throw new NumberFormatException("Wrong input: " + s);
			}
			
			String strReal = s.substring(0, delimiter);
			String strImag = s.substring(delimiter, s.length()-1);
			double real = Double.parseDouble(strReal);
			double imag = Double.parseDouble(strImag);
			return new ComplexNumber(real, imag);
		}
	}

	/**
	 * Returns the real part of this complex number.
	 * 
	 * @return the real part of this complex number
	 */
	public double getReal() {
		return real;
	}

	/**
	 * Returns the imaginary part of this complex number.
	 * 
	 * @return the imaginary part of this complex number
	 */
	public double getImag() {
		return imag;
	}
	
	/**
	 * Returns the magnitude of this complex number from the polar form
	 * representation.
	 * 
	 * @return the magnitude of this complex number from the polar form
	 */
	public double getMagnitude() {
		return Math.hypot(real, imag);
	}

	/**
	 * Returns the angle of this complex number from the polar form
	 * representation.
	 * 
	 * @return the angle of this complex number from the polar form
	 */
	public double getAngle() {
		return Math.atan2(imag, real);
	}
	
	/**
	 * Performs addition of the two complex numbers by formula
	 * <code>this + c</code> and returns the result.
	 * 
	 * @param c complex number to be added to this one
	 * @return the sum of the two complex numbers
	 */
	public ComplexNumber add(ComplexNumber c) {
		return new ComplexNumber(real+c.real, imag+c.imag);
	}
	
	/**
	 * Performs subtraction of the two complex numbers by formula
	 * <code>this - c</code> and returns the result.
	 * 
	 * @param c complex number to be subtracted from this one
	 * @return the difference of the two complex numbers
	 */
	public ComplexNumber sub(ComplexNumber c) {
		return new ComplexNumber(real-c.real, imag-c.imag);
	}
	
	/**
	 * Performs multiplication of the two complex numbers by formula
	 * <code>this * c</code> and returns the result.
	 * 
	 * @param c complex number to be multiplied with this one
	 * @return the product of the two complex numbers
	 */
	public ComplexNumber mul(ComplexNumber c) {
		double realMul = real*c.real - imag*c.imag;
		double imagMul = imag*c.real + real*c.imag;
		return new ComplexNumber(realMul, imagMul);
	}
	
	/**
	 * Performs division of the two complex numbers by formula
	 * <code>this / c</code> and returns the result. Special case of this method
	 * is when the complex denominator is <code>0+0i</code>, then the
	 * {@linkplain IllegalArgumentException} is thrown.
	 * 
	 * @param c complex number to be divided with this one
	 * @return the quotient of the two complex numbers
	 * @throws IllegalArgumentException if <code>c</code> is 0+0i
	 */
	public ComplexNumber div(ComplexNumber c) {
		ComplexNumber conjugated = new ComplexNumber(c.real, -c.imag);
		
		ComplexNumber numerator = this.mul(conjugated);
		double denominator = c.mul(conjugated).getReal(); // here imag is 0.0
		if (denominator < ZERO_LIMIT) {
			throw new ArithmeticException("Division by zero: " + c);
		}
		
		double realDiv = numerator.getReal() / denominator;
		double imagDiv = numerator.getImag() / denominator;
		
		return new ComplexNumber(realDiv, imagDiv);
	}
	
	/**
	 * Performs the power operation of this complex number and n by formula
	 * <code>this ^ n</code> and returns the result. If n is less than 0, an
	 * {@linkplain IllegalArgumentException} is thrown.
	 * 
	 * @param n complex number to be raised to the power n
	 * @return the result of the power operation
	 * @throws IllegalArgumentException if <code>n < 0</code>
	 */
	public ComplexNumber power(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("n must be greater than or equal to 0.");
		}
		
		double magnitude = Math.pow(getMagnitude(), n);
		double angle = n*getAngle();
		
		return fromMagnitudeAndAngle(magnitude, angle);
	}
	
	/**
	 * Calculates and returns an array of root solutions calculated by formula<br>
	 * <code>r^(1/n) * [cos((phi + 2*k*pi)/n) + i*sin((phi + 2*k*pi)/n)]</code>
	 * 
	 * @param n n-th root to be calculated
	 * @return an array of root solutions
	 * @throws IllegalArgumentException if <code>n <= 0</code>
	 */
	public ComplexNumber[] root(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("n must be greater than 0.");
		}
		
		double magnitude = Math.pow(getMagnitude(), 1.0/n);
		double angle = getAngle();
		
		ComplexNumber[] solutions = new ComplexNumber[n];
		for (int k = 0; k < n; k++) {
			solutions[k] = fromMagnitudeAndAngle(magnitude, (angle + 2*k*Math.PI) / n);
		}
		
		return solutions;
	}
	
	/**
	 * Returns the formatted string of this complex number with real and
	 * imaginary part rounded to three decimal places. The returned string of a
	 * complex number <code>2.57 + 3.141593i</code> will look like this:
	 * <code>2.570 + 3.142i</code>
	 */
	@Override
	public String toString() {
		if (imag < 0) {
			return String.format((Locale) null, "%.3f - %.3fi", real, -imag);
		} else {
			return String.format((Locale) null, "%.3f + %.3fi", real, imag);
		}
	}
}
