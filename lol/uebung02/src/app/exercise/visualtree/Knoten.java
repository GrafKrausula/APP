package app.exercise.visualtree;
/**
 * Die Java-Klasse BSTree.
 *
 * @author Marcel Neitzel
 */

import app.exercise.algebra.CompRational;

 /**
  * Die Knoten-Klasse.
  */
public class Knoten implements DrawableTreeElement<CompRational> {
	// == Klassenvariablen ====================================

	/** Speichert den Wert des Knotens */
	public CompRational value;
	/** Die Unterbäume bzw. Blätter */
	public Knoten lbs; // leftBSTree
	public Knoten rbs; // rightBSTree

	// == Konstrukoren ========================================

	 // default constructor -----------------------------------
	 /**
	  * Der Default-Konstruktor. Er erzeugt ein zulässiges Objekt,
	  * erwartet jedoch keine Argumente.
	  */
	 public Knoten() {
	 	value = new CompRational();
	 	lbs = null;
	 	rbs = null;
	 }

	/** Argumentkonstruktor - bekommt eine CompRational übergeben */
	public Knoten(CompRational cr) {
		new Knoten();
		value = cr;
	}


	/**
	 * Gibt den BSTree mit den linken kleineren als in value gespeicherten
	 * Werten zurück.
	 * @return DrawableTreeElement<CompRational>
	 */
	public DrawableTreeElement<CompRational> getLeft() {
		return lbs;
	}
	/**
	 * Gibt den BSTree mit den rechten größeren als in value gespeicherten
	 * Werten zurück.
	 * @return DrawableTreeElement<CompRational>
	 */
	public DrawableTreeElement<CompRational> getRight() {
		return rbs;
	}

	/**
	 * Gibt zurück, ob der Knoten rot ist.
	 * @return boolean
	 */
	public boolean isRed() {
		return true;
	}
	/**
	 * Gibt den Wert des Knotens zurück.
	 * @return CompRational value
	 */
	public CompRational getValue() {
		return value;
	}
}
