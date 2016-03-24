package hr.fer.zemris.java.tecaj.hw2.demo;

import java.util.Arrays;

import hr.fer.zemris.java.tecaj.hw2.ComplexNumber;

/**
 * This program demonstrates the usage of {@linkplain ComplexNumber} class.
 *
 * @author Mario Bobic
 */
public class ComplexDemo {

	/**
	 * Program entry point.
	 * 
	 * @param args not used in this demo
	 */
	public static void main(String[] args) {
		ComplexNumber c1 = new ComplexNumber(2, 3);
		ComplexNumber c2 = ComplexNumber.parse("2.5-3i");
		ComplexNumber c3 = c1
				.add(ComplexNumber.fromMagnitudeAndAngle(2, 1.57))
				.div(c2)
				.power(3)
				.root(2)[1];
		System.out.println(c3);
		
		
		System.out.println();
		
		System.out.println(ComplexNumber.parse("2.5-3i"));
		System.out.println(ComplexNumber.parse("2.5"));
		System.out.println(ComplexNumber.parse("-3i"));
		System.out.println(ComplexNumber.parse("0"));
		System.out.println(ComplexNumber.parse("10 + 10i"));
//		System.out.println(ComplexNumber.parse("20i + 20"));
//		System.out.println(ComplexNumber.parse("30+30"));
//		System.out.println(ComplexNumber.parse("10+i"));
		
		System.out.println();
		
		
		ComplexNumber a = new ComplexNumber(2, 2);
		ComplexNumber b = new ComplexNumber(10, 10);
		int n = 5;
		
		System.out.println("a+b = " + a.add(b));
		System.out.println("a-b = " + a.sub(b));
		System.out.println("a*b = " + a.mul(b));
		System.out.println("a/b = " + a.div(b));
		System.out.println("a^n = " + a.power(n));
		System.out.println("n√a = " + Arrays.toString(a.root(n)));
		
//		System.out.println();
//		ComplexNumber i = ComplexNumber.fromImaginary(1);
//		ComplexNumber zero = ComplexNumber.parse("0");
//		System.out.println("i / 0 = " + i.div(zero));
	}
	
}
