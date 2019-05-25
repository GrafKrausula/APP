package app.exercise.algebra;

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
    public int compareTo(Object o){

        if(!(o instanceof CompRational)) throw new NullPointerException();
        if(o == null) throw new ClassCastException();

        this.sub((Rational) o);

        if(this.getN() < 0) return -1;

        if((this.getN() == 0) && (this.equals(o)) return 0;

        if(this.getN() > 0) return 1;

        return 0;

    }


}
