import java.io.*;
import java.lang.Math;
public class PolynomialGF2{

  private boolean b_array[];
  private boolean copyb_array[];

  private int n;
  private int hash;
  private final String ONE = "1";
  private final String ZERO = "0";
  private String polynom;

  public PolynomialGF2(){
    this.polynom = ONE;
    //Default Konstruktor der 1 zurückliefern soll
  }

  public PolynomialGF2(boolean[] b_array){
    this.b_array = b_array.clone();
    this.hash = hashCode(trim(this.b_array));
  }

  public int getHash(){
    return this.hash;
  }

  public boolean[] toArray(boolean[] b_array){
    this.copyb_array = this.b_array.clone();
    return copyb_array;
  }

  private boolean[] trim(boolean[] bigArray){
    boolean trimmedArray[];

    if( (isZero(bigArray) == false) && (isOne(bigArray) == false) ){
      for(int i = (bigArray.length -1); i >= 0; i--){
        if(bigArray[i] == true){
          trimmedArray = new boolean[i+1];
          for(int k = 0; k <= i; k++){
            trimmedArray[k] = bigArray[k];
            System.out.print(trimmedArray[k] + "\t");
          }
          System.out.print("\n");
          return trimmedArray;
        }
      }
    }
    return bigArray;

  }

  private int hashCode(boolean[] array){
    int hash = 0;
    int a;
    for(int i = 0; i < array.length; i++){
      if(array[i] == true){
        a = 1;
      } else {
        a = 0;
      }
      System.out.print(hash + " + ( " + a + " * " + "2^" + i + " (" + (int) Math.pow(2,i) + ") )");
      hash +=  a * (int) Math.pow(2,i);
      System.out.println(" = " + hash);
    }
    return hash;
  }

  private boolean isZero(boolean[] b_array){
    if(this.b_array.length == 0){
      return true;
    } else {
      return false;
    }
  }

  private boolean isOne(boolean[] b_array){
    if(this.b_array.length == 1){
      return true;
    } else {
      return false;
    }
  }

  /*private PolynomialGF2 clone(boolean[] b_array){

  }

  public PolynomialGF2 plus(PolynomialGF2 polynom1){

  }

  public PolynomialGF2 times(PolynomialGF2 polynom1){

  }

  public PolynomialGF2 mod(PolynomialGF2 polynom1){

  }


  private int degree(PolynomialGF2 polynom1){
    int grad;
    //berechnung des grads durch polynom
    return grad;
  }

  private PolynomialGF2 shift(int k){

  }

  */

}
