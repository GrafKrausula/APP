package app.exercise.algebra;
/**
 * Eine Java-Klasse Rational.
 *
 * @author Martin Krause
 */

import java.lang.Math;


public class Rational extends BasisFraction {
 // private -----------------------------------------------
	 /** Der Zähler der rationalen Zahl. */
	 private long		numerator;
	 /** Der Nenner der rationalen Zahl. */
	 private long		denominator;


	 // == Konstrukoren ========================================
	 // default constructor -----------------------------------
	 /**
	  * Der Default-Konstruktor. Er erzeugt ein zulässiges Objekt,
	  * erwartet jedoch keine Argumente.
	  */
	 public Rational() {
	 	this.numerator = 0;
	 	this.denominator = 1;
	 }
	 // argument constructor ----------------------------------
	 /**
	  * Der Konstruktor, der Argumente akzeptiert. Mithilfe des
	  * Euklidischen Algorithmus wird die Zahl in Normalform ge-
	  * bracht.
	  * @param n eine ganze Zahl
	  * @param d eine ganze Zahl
	  */
	 public Rational(long n, long d) {
	 	setND(n, d);
	 }
	 // copy constructor --------------------------------------
	 /**
	  * Der Konstruktor zum kopieren einer rationalen Zahl.
	  * Es werden die unten definierten Setz-/Lesemethoden ver-
	  * wendet.
	  * @param r eine Instanz der Klasse Rational
	  */
	 public Rational(Rational r) {
	 	this.numerator = r.numerator;
	 	this.denominator = r.denominator;
	 }

	 // ~~ Methoden ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 // == Klassenmethoden =====================================


	 /**
	  * Euklidischer Algorithmus
	  * Bestimmt den größten gemeinsamen Teiler von zwei natürlichen
	  * Zahlen n und d.
	  * @param n eine natürliche Zahl
	  * @param d eine natürliche Zahl
	  * @return den ggT (größte gemeinsame Teiler).
	  */
	 private long gcd (long n, long d) {
	 	long t;
	 	if (n == 0) {
	 		t = d;
	 	} else {
	 		while (d != 0) {
	 			if (n > d) {
	 				n = n - d;
	 			} else {
	 				d = d - n;
	 			}
	 		}
	 		t = n;
	 	}
	 	return t;
	 }

	 // == Fractional ==========================================
	/**
	 * Gibt den Zähler einer Instanz von Rational zurück
	 * @return den Zähler einer Instanz von Rational
	 */
	public long getN() {
		return this.numerator;
	}

	/**
	 * Gibt den Nenner einer Instanz von Rational zurück
	 * @return den Nenner einer Instanz von Rational
	 */
	public long getD() {
		return this.denominator;
	}

	// == BasisFraction =========================================

	/**
	 * Setzt den Nenner und Zähler einer Instanz Rational
	 * @param numerator eine ganze Zahl
	 * @param denominator eine ganze Zahl
	 */
	public void setND(long numerator, long denominator) {
		// Prüfen des auf positives Vorzeichen des Nenners. Der
	 	// Zähler hat automatisch das richtige Vorzeichen.
	 	if (denominator < 0) {
	 		numerator *= -1;
	 		denominator *= -1;
	 		}
	 	// Zufällig erstellter denominator = 0?
	 	if (denominator == 0 ) {
	 		denominator = 1;
	 	}
	 	// der größte gemeinsame Teiler
	 	long t = gcd(Math.abs(numerator), Math.abs(denominator));
	 	// Normierung von Zähler und Nenner
		this.numerator = numerator / t;
		this.denominator = denominator / t;
	}

	/**
	 * Berechnet das Reziproke einer Instanz von Rational
	 * @return eine Instanz von Rational
	 */
	public Fractional reciprocal() {
		return new Rational(this.denominator, this.numerator);
	}

	/*
	 * Berechnet das additive Inverse einer Instanz von Rational
	 * @return eine Instanz von Rational
	 */
	public Fractional negation () {
		return new Rational(this.numerator*(-1), this.denominator);
	}
	// == Object =================================================

	/**
	 * Wandelt eine Instanz der Klasse Rational in einen aus-
	 * gabefähigen String um.
	 * @return Ein ausgabefähiger String
	 */
	public String toString() {
		return "" + this.getN() + "/" + this.getD() + "";
	}


	/**
	 * Klont eine Instanz der Klasse Rational.
	 * @return Eine neue Instanz der Klasse Rational
	 */
	protected Rational clone() {
		return new Rational(this);
	}

	/**
	 * Testet zwei Instanzen der Klasse Rational auf Gleichheit und prüft,
	 * ob die Bedingungen Transitivität, Reflexivität, Symmetrie
	 * und Consistent erfüllt sind.
	 * @param o Eine Instanz der Klasse Object oder abgeleitete Klasse.
	 * @return Ein Boolean, der bei Gleichheit true ist, sonst false.
	 */
	@Override
	public boolean equals(Object o) { 
		if( ! ( o instanceof Rational ) ) {
			return false;
		}
		Rational r = (Rational) o;
		return this.numerator == r.getN() && this.denominator == r.getD();
	}
	/**
	 * Berechnet einen Hashcode
	 * @return einen int-Hashcode
	 */
	public int hashCode() {
		return this.toString().hashCode();
	}




}
