package app.exercise.algebra;

import java.util.*;

public class CompRational extends Rational implements java.lang.Comparable<CompRational>{

    public CompRational(){
      super();
    }

    public CompRational(long n, long d){
      super(n,d);
    }

    public CompRational(CompRational r){
      super(r);
    }

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
