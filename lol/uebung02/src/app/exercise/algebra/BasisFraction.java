package app.exercise.algebra;
/**
 * Eine Java-Klasse BasisFraction.
 *
 * @author Martin Krause
 */


 /**
  * Die Hauptklasse
  */
abstract class BasisFraction implements Fractional {
	/**
	 * Methode zum Setzen von Zähler und Nenner
	 */
	protected abstract void setND(long numerator, long denominator);

	// == Fractional ===============================================
	/**
	 * addiert zwei Fractionals
	 */
	public void add(Fractional operand) {
		long n1 = getN();
		long n2 = operand.getN();
		long d1 = getD();
		long d2 = operand.getD();
		long n = n1*d2 + n2*d1;
		long d = d2*d1;
		setND(n, d);
	}
	/**
	 * substrahiert zwei Fractionals
	 */
	public void sub(Fractional operand) {
		this.add(operand.negation());
	}
	/**
	 * multizliziert zwei Fractionals
	 */
	public void mul(Fractional operand) {
		setND(getN() * operand.getN(), getD() * operand.getD());
	}
	/**
	 * dividiert zwei Fractionals
	 */
	public void div(Fractional operand) {
		this.mul(operand.reciprocal ());
	}

	/**
	 * Berechnet das Reziproke einer Instanz von BasisFraction
	 */
	abstract public Fractional reciprocal ();


	/**
	 * Berechnet das additive Inverse einer Instanz von BasisFraction
	 */
	abstract public Fractional negation ();




}
