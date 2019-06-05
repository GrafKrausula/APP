package app.exercise.adt;
/**
 * Die Java-Klasse BSTree. (ADT... Abstract Data Type)
 *
 * @author Martin Krause
 */

import app.exercise.algebra.CompRational;
import app.exercise.visualtree.Knoten;
import java.util.AbstractCollection;
import java.util.Iterator;

 /**
  * Diese Klasse realisiert einen binären Suchbaum.
  */
public class BSTree extends AbstractCollection<Knoten> {

	public Knoten k;

	// == Konstrukoren ========================================

	 // default constructor -----------------------------------

	 /**
	  * Der Default-Konstruktor. Er erzeugt ein zulässiges Objekt,
	  * erwartet jedoch keine Argumente.
	  */
	 public BSTree() {
	 	super();
	 	k = null;
	 }

	 /**
	  *	Dieser Konstruktor erzeugt einen Baum mit cr als rootKnoten.
	  */
	 public BSTree(CompRational cr) {
	 	super();
	 	k = new Knoten(cr);
	 }


	// == Klassen-Methoden ======================================

	/**
	 * Liefert die Anzahl der Elemente im BSTree zurück
	 * @return int Anzahl der Elemente im BSTree
	 */
	public int size() {
		int c = 0;
		for (Iterator<Knoten> j = this.iterator(); j.hasNext();) {
			j.next();
			c++;
			}
		return c;
	}

	/**
	 * Überprüft, ob der aktuelle Knoten leer ist
	 * @return boolean false, wenn nicht leer, true leer
	 */
	public boolean isEmpty() {
		if (k == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Überprüft, ob eine Instanz CompRational im BSTree enthalten ist
	 * @return boolean true, falls die Instanz enthalten ist, false wenn nicht
	 */
	public boolean contains(Object o) {
		Knoten nex;
		for (Iterator<Knoten> j = this.iterator(); j.hasNext();) {
				nex = j.next();
				if(nex.getValue().compareTo((CompRational) o) == 0) {
					return true;
				}
			}
		return false;

	}

	/**
	 * Prüft, ob eine AbstractCollection im BSTree enthalten ist
	 * @param c - ein Unterbaum
	 * @return Boolean - ist enthalten oder nicht.
	 */
	public boolean containsAll(AbstractCollection<?> c) {
		System.out.println("los gehts");
		for (Iterator<?> j = c.iterator(); j.hasNext();)
			if (! (this.contains(((Knoten) j.next()).getValue())))
				return false;
		return true;
	}

	/**
	 * Wandelt den Inhalt des Baums in ein Array um
	 * @return Object-Array - Baum als ObjectArray
	 */
	public Object[] toArray() {
		// Erstelle Array der Größe des Baumes
		Object[] A = new Object[this.size()];
		int i = 0;
		// Schreibe alle Knoten in das Array
		for (Iterator<Knoten> j = this.iterator(); j.hasNext();) {
			A[i] = j.next();
			i++;
		}
		return A;
	}

	/**
	 * Wandelt den RootKnoten in einen Ausgabefähigen String
	 * @return String, der den Rootknoten angibt.
	 */
	public String toString() {
		return k.getValue().toString();
	}

	/**
	 * Fügt ein neues Element in den BSTree
	 * @param  cr - eine CompRational
	 * @return true - bei Erfolg
	 *		   false - bei Fehler
	 */
	public boolean add(CompRational cr) {
		Knoten lk = k; // Laufknoten
		while (true) {
			// Setze gegebenenfalls rootKnoten
			if (this.isEmpty()) {
				k = new Knoten(cr);
				return true;
			} else {
				// Prüfe, ob CompRational bereits im Baum existiert
				if (this.contains(cr)) {
					System.out.println("Duplikat!");
					return false;
				} else {
					// Prüfe linken Unterbaum
					if (lk.getValue().compareTo(cr) > 0) {
						// Bei Blatt, füge Zahl hinzu
						if(lk.lbs == null) {
							lk.lbs = new Knoten(cr);
							return true;
						// Sonst führe Suche in linkem Unterbaum fort
						} else {
							lk = lk.lbs;
						}
					// Sonst prüfe rechten Unterbaum
					} else {
						// Bei Blatt, füge Zahl hinzu
						if(lk.rbs == null) {
							lk.rbs = new Knoten(cr);
							return true;
						// Sonst führe Suche in rechtem Unterbaum fort
						} else {
							lk = lk.rbs;
						}
					}
				}
			}
		}
	}

	/**
	 * Enternt ein Element aus dem Baum
	 * @param o
	 * @return Boolean true - bei Erfolg
	 *         		   false - bei Fehler
	 */
	public boolean remove(Object o) {

		/* Caste o nach Knoten */
		if( ! ( o instanceof CompRational ) ) {
			System.out.println("InstanzFehler");
			return false;
		}

		/* Finde Knoten */
		Knoten Z = new Knoten();

		for (Iterator<Knoten> j = this.iterator(); j.hasNext();) {
			Z = j.next();
			if(Z.getValue().compareTo((CompRational) o) == 0) {
					break;
				}
			}

		/* == 1. Fall: Z hat zwei Blätter == */
		if (Z.lbs == null && Z.rbs == null) {

			// finde topKnoten von Z
			Knoten topK = new Knoten();
			Iterator<Knoten> j2 = new BSTreeIterator(k);
			for (;j2.hasNext();) {
				topK = j2.next();
				if (topK.lbs != null) {
					if (topK.lbs.getValue().compareTo((CompRational) o) == 0) {
						// Z im lbs gefunden
						topK.lbs = null;
						break;
					}
				}
				if (topK.rbs != null) {
					if (topK.rbs.getValue().compareTo((CompRational) o) == 0) {
						// Z im kbs gefunden
						topK.rbs = null;
						break;
					}
				}
			}
			return true;

		/* == 3. Fall: Z hat kein Blatt == */
		} else if (Z.lbs != null && Z.rbs != null) {
			/* suche N */
			Knoten N = Z.rbs; // 1. Schritt nach rechts
			while (N.lbs != null) { // linke Unterbäume durchlaufen
				N = N.lbs;
			}
			/* Speichere Wert von N um ihn in Z zu kopieren */
			CompRational nvalue = new CompRational();
			nvalue = N.getValue();
			/* Lösche N */
			if(this.remove(N.getValue()) == false) {
				System.out.println("Konnte N nicht löschen!");
				return false;
			}
			/* Speichere Wert von N in Z */
			Z.value = nvalue;

		/* 2. Fall: Z hat ein Blatt	== */
		} else {
			if (Z.lbs != null) {
				Z.value = Z.lbs.getValue();
				// Evtl. vorhandene Unterbäume von rbs mitkopieren
				if (Z.lbs.rbs != null) {
					Z.rbs = Z.lbs.rbs;
				}
				Z.lbs = null;

			} else if (Z.rbs != null) {
				Z.value = Z.rbs.getValue();
				// Evtl. vorhandene Unterbäume von lbs mitkopieren
				if (Z.rbs.lbs != null) {
					Z.lbs = Z.rbs.lbs;
				}
				Z.rbs = null;

			}
		}
		return true;
	}

	/**
	 * Ein Iterator zum iterrieren über die Knoten
	 */
	public Iterator<Knoten> iterator() {
		return new BSTreeIterator(this.k);
	}
	/**
	* Throws Unsupported Operation Exception for BSTree Class
	*/
	public boolean addAll(AbstractCollection<? extends Knoten> c) {
		throw new UnsupportedOperationException("Unsupported Operation by Class BSTree");
	}

	/**
	* Throws Unsupported Operation Exception for BSTree Class
	*/
	public void clear() {
		throw new UnsupportedOperationException("Unsupported Operation by Class BSTree");
	}




}
