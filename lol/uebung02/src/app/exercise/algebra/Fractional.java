package app.exercise.algebra;
/**
 * Eine Java-Klasse BasisFraction.
 *
 * @author Martin Krause
 */

/**
 * Das Interface Fractional
 */
public interface Fractional {
	// get numerator
	/**
	 * Gibt den Numerator aus
	 */
	long getN();
	// get denominator
	/**
	 * gibt den Denominator aus
	 */
	long getD();
	// add operand to object

	/**
	 * Addiert operand auf this
	 */
	void add(Fractional operand);
	// subtract operand from object
	/**
	 * Substrahiert operand von this
	 */
	void sub(Fractional operand);
	// multiply object by operand
	/**
	 * multiplieziert operand auf this
	 */
	void mul(Fractional operand);
	// divide object by operand
	/**
	 * dividiert operand von this
	 */
	void div(Fractional operand);
	// new additive inverse object
	/**
	 * Gibt das additive Inverse zurück
	 */
	Fractional negation ();
	// new multiplicative inverse object
	/**
	 * Gibt das Reziproke zurück
	 */
	Fractional reciprocal ();
}
