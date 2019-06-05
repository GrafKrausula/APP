package app.exercise.adt;
/**
 * Die Java-Klasse BSTree.
 *
 * @author Martin Krause
 */

import app.exercise.visualtree.*;
import java.util.Iterator;
import app.exercise.adt.*;
import app.exercise.algebra.*;

 /**
  * Diese Klasse realisiert einen Iterator über einen binären Suchbaum.
  */
public class BSTreeIterator implements Iterator<Knoten> {


	public Knoten k;
	static public CompRational prevValue;
	static public Knoten nextCandidate;

	/** Der Konstruktor des Iterators */
	public BSTreeIterator(Knoten k) {
	 	this.nextCandidate = k;
	 	this.prevValue = new CompRational(0,1);
	 	this.nextCandidate = new Knoten();
	 	this.k = k;
	 }

	/**
	 * Prüft ob der Iterator mehrere Elemente bzw. ein naechstes Element hat
	 * @return Boolean true, falls es ein naechstes Element gibt, andernfalls false
	 */
	public boolean hasNext() {
		Knoten lk = k;
		while (true) {
			if (lk.getValue().compareTo(prevValue) == 1) {
				return true;
			} else {
				if (lk.getRight() != null) {
					lk = (Knoten) lk.getRight();
				} else {
					return false;
				}
			}
		}

	}

	/**
	 * Liefert das nächste Element in der Iteration zurück
	 * @return Knoten - der nächste Knoten in der Iteration.
	 */
	public Knoten next() {

		Knoten lk = k; // Laufknoten

		while (true) {
			// Ist das letzte Element kleiner als die momentane Wurzel, dann
			// prüfe linken Baum.
			if (prevValue.compareTo(lk.getValue()) == -1 ) {
				// Möglicher Nachfolger
				nextCandidate = lk;
				// Ist der linke Unterbaum vorhanden, prüfe diesen
				if (lk.getLeft() != null) {
					lk = (Knoten) lk.getLeft();
				} else {
					prevValue = lk.getValue();
					lk = k;
					return nextCandidate;
				}
			}
			// Analog rechter Baum
			if (prevValue.compareTo(lk.getValue()) == 1) {

				if (lk.getRight() != null) {
					lk = (Knoten) lk.getRight();
				} else {
					prevValue = lk.getValue();
					lk = k;
					return nextCandidate;
				}
			}
			// gleicher Fall
			if (prevValue.compareTo(lk.getValue()) == 0) {
				if (lk.getRight() != null) {
					lk = (Knoten) lk.getRight();
				} else {
					prevValue = nextCandidate.getValue();
					lk = k;
					return nextCandidate;
				}

			}
		}
	}

	/**
	* Throws Unsupported Operation Exception for BSTreeIterator Class
	*/
	public void remove() {
		throw new UnsupportedOperationException("Unsupported Operation by Class BSTree");
	}

	/**
	* Throws Unsupported Operation Exception for BSTreeIterator Class
	*/
	public void forEachRemaining(DrawableTreeElement<? super CompRational> action) {
		throw new UnsupportedOperationException("Unsupported Operation by Class BSTree");
	}
}
