package app.exercise.algebra;

/**
 * Eine abstrakte Java-Klasse BasisFraction.
 *
 * @author Martin Krause
 */

abstract class BasisFraction implements Fractional {

protected abstract void setND ( long numerator , long denominator );


// add operand to object
public void add (Fractional operand){
  this.setND((this.getN() * operand.getD()) + (this.getD() * operand.getN()), this.getD() * operand.getD());
}
// subtract operand from object
public void sub (Fractional operand){
  this.add(operand.negation());
}

// multiply object by operand
public void mul ( Fractional operand ){
  this.setND(this.getN() * operand.getN() , this.getD() * operand.getD());
}
// divide object by operand
public void div ( Fractional operand ){
  this.mul(operand.reciprocal());
}

public abstract Fractional negation ();

public abstract Fractional reciprocal ();


}
