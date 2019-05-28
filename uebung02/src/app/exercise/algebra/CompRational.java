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
    * Vergleicht zwei comprationals,
    * Dieses comprational wird immer mit einem zweiten, o,
    * verglichen.
    * Der vergleich wird durch subtraktion als, this-o gelöst.
    * Führt die subraktion zu einem negativen bruch, so war o größer.
    * Ist das ergebnis 0/0 so waren sie gleich groß
    * wenn der resultierende bruch positiv, so war o kleiner.
    * @param o ein zu kopierendes comparable rational
    * @return r Ergebnis des vergleichs this mit o; -1 bei this kleiner, 0 bei gleich, 1 bei this größer
    */


    @Override
    public int compareTo(CompRational o){

        if(!(o instanceof CompRational)) throw new NullPointerException();
        if(o == null) throw new ClassCastException();

        this.sub((Rational) o);

        if(this.getN() < 0) return -1;

        if((this.getN() == 0) && (this.equals(o))) return 0;

        if(this.getN() > 0) return 1;

        return 0;

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
