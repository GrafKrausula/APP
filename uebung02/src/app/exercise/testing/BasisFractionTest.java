package app.exercise.testing;

/**
 * Klasse, zum Testen der Klasse BasisFraction und Rational.
 */

import app.exercise.algebra.Rational;
import app.exercise.algebra.CompRational;

public class BasisFractionTest {
	/**
	 * Gibt die Tests aus.
	 */
	public static void main( String[] args ) {
		/* Erstellen von Testinstanzen */
		Rational r1		= new Rational();
		Rational r2		= new Rational(3, 12);
		Rational r3		= new Rational(-3, -12);
		Rational r4 	= new Rational(4, -25);
		CompRational cr1 = new CompRational(3, 6);
		CompRational cr2 = new CompRational(1, 2);
		CompRational cr3 = new CompRational(1, 6);


		System.out.println("\nTesten der Methode toString() und der korrekten Normform");
		System.out.println("r1: " + r1.toString());
		System.out.println("r2: " + r2.toString());


		System.out.println("Wird r3 = -3/-12 richtig dargestellt?: " + r3.toString());
		System.out.println("Wird r4 = 4/-25 richtig dargestellt?: " + r4.toString());

		System.out.println("\nTest der Methode equals:");
		System.out.println("Sind r2 und r3 gleich? "+r2.equals(r3));
		System.out.println("Sind r1 und r3 gleich? "+r1.equals(r3));

		System.out.println("\nTest der Methode hashCode:");
		System.out.println("Hashcode von r1: " + r1.hashCode());

		System.out.println("\nTesten von add, sub, mul und div:");
		System.out.println(r1.toString() + " " + r2.toString());
		r1.add(r2);
		System.out.println("r1 + r2: " + r1.toString() + "\n");
		System.out.println(r1.toString() + " " + r2.toString());
		r1.add(r2);
		System.out.println("r1 + r2: " + r1.toString() + "\n");

		System.out.println(r1.toString() + " " + r2.toString());
		r1.sub(r2);
		System.out.println("r1 - r2: " + r1.toString() + "\n");
		System.out.println(r1.toString() + " " + r2.toString());
		r1.sub(r2);
		System.out.println("r1 - r2: " + r1.toString() + "\n");

		r1.mul(r2);
		System.out.println("r1 * r2: " + r1.toString());
		r1.mul(r2);
		System.out.println("r1 * r2: " + r1.toString());

		r1.div(r2);
		System.out.println("r1 / r2: " + r1.toString());
		r1.div(r2);
		System.out.println("r1 / r2: " + r1.toString());

		System.out.println("\nTesten der CompRational-Klasse");
		System.out.println(cr1 + " compared to " + cr2 + ": " + cr1.compareTo(cr2));
		System.out.println(cr1 + " compared to " + cr3 + ": " + cr1.compareTo(cr3));
		System.out.println(cr3 + " compared to " + cr2 + ": " + cr3.compareTo(cr2));

	}
}
