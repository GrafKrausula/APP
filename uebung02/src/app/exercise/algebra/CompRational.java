package app.exercise.algebra;
/**
 * Eine Java-Klasse CompRational.
 *
 * @author Martin Krause
 */
import java.util.*;

public class CompRational extends Rational implements java.lang.Comparable<CompRational>{


  /**
   * Erstellt ein default comparable CompRational aus einem Object Rational
   */
    public CompRational(){
      super();
    }
  /**
    * Erstellt ein comparable Rational aus einem Object Rational
    * @param n Nenner,eine ganze Zahl
    * @param d Zähler,eine ganze Zahl
    */
    public CompRational(long n, long d){
      super(n,d);
    }
  /**
    * Kopiert ein CompRational
    * @param r ein zu kopierendes comparable rational
    */

    public CompRational(CompRational r){
      super(r);
    }

    /**
  	 * Implementiert die Methode compareTo aus java.lang.Comparable
  	 * @param o ein Object
  	 * @return ein Integer, der negativ bei <, null bei == und positiv
  	 *		   bei > ist.
  	 */
  	public int compareTo(CompRational o) {
  		try {
  			if( ! ( o instanceof CompRational ) ) {
  				throw new ClassCastException("o is not of type CompRational");
  			}

  				long d1 = o.getD();
  				long d2 = getD();
  				long n1 = o.getN();
  				long n2 = getN();

  				if (d1 == d2 && n1 == n2)
  					return 0;
  				n1 *= d2;
  				n2 *= d1;
  				if ( n1 < n2 )
  					return 1;
  				else
  					return -1;


  		} catch (ClassCastException e) {
  			System.out.println("Es ist ein Typ-Fehler aufgetreten");
  			return 0;
  		}
  	}

    /**
    * Zufaellige CompRationals in gegebenen Grenzen
    * @param min untere Grenze
    * @param max obere Grenze
    */
    public void random(CompRational min, CompRational max) {
      Random r = new Random();
      CompRational cr = new CompRational();

      long n = 1;
      long d = 1;

      boolean kleinermax = false;
      boolean groessermin = false;

      while (kleinermax == false || groessermin == false) {
        kleinermax = false;
        groessermin = false;


        n = r.nextInt(10) + 1;
        d = r.nextInt(10) + 1;
        cr.setND(n, d);

        if (cr.compareTo(max) == -1)
          kleinermax = true;
        else
          kleinermax = false;

        if (cr.compareTo(min) == 1)
          groessermin = true;
        else
          groessermin = false;
        }
        this.setND(n, d);
      }


}
