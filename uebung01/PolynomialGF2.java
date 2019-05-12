import java.io.*;
import java.lang.Math;
public class PolynomialGF2{

  private boolean k_array[];
  //private boolean copyk_array[];
  private int id;
  private int degree;
  private int n;
  private int hash;
  private final String ONE = "1";
  private final String ZERO = "0";
  private String polynom;

  static int nextID = 0;
  static int updateNextID() {
    return nextID++;
  }

  public PolynomialGF2(){
    this.polynom = ONE;
    this.k_array = new boolean[1];
    this.k_array[0] = true;

    //Default Konstruktor der 1 zurückliefern soll
  }

  public PolynomialGF2(boolean[] k_array){
    //if k_array isZero && isOne == false mache weiter
    this.id = nextID++;
    this.k_array = trim(clone(k_array));
    this.hash = hashCode(this.k_array);
    this.degree = degree(this);

  }

  public int getHash(){
    return this.hash;
  }

  public boolean[] toArray(boolean[] k_array){
    //this.copyk_array = clone(this.k_array);
    return clone(this.k_array);
  }

  public String toString(){
    String polyString = "Polynom" + id + ": ";
    for(int i = 0; i <= this.degree; i++){
      polyString += this.k_array[i] + " ";
    }
    polyString += "\nDEGREE: " + this.degree + "\n" + "Hash: " + hash + "\n\n";

    return polyString;
  }

  private boolean[] trim(boolean[] bigArray){
    boolean trimmedArray[];

    if( (isZero(bigArray) == false) && (isOne(bigArray) == false) ){
      for(int i = 0; i < bigArray.length; i++){
        if(bigArray[i] == true){
          trimmedArray = new boolean[bigArray.length-i];
          for(int k = 0+i; k < bigArray.length; k++){
            trimmedArray[k-i] = bigArray[k];
          }
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
      //    System.out.print(hash + " + ( " + a + " * " + "2^" + i + " (" + (int) Math.pow(2,i) + ") )");
      hash +=  a * (int) Math.pow(2,i);
      //    System.out.println(" = " + hash);
    }
    return hash;
  }

  private boolean isZero(boolean[] k_array){
    if(k_array.length == 0){
      return true;
    } else {
      return false;
    }
  }

  private boolean isOne(boolean[] k_array){
    if(k_array.length == 1){
      return true;
    } else {
      return false;
    }
  }

  private boolean[] clone(boolean[] k_array, int opt_wrte_strt){
    int length = k_array.length;
    boolean c_array[] = new boolean[length+opt_wrte_strt];

    for(int k = 0; k < length; k++){
      c_array[k+opt_wrte_strt] = k_array[k];
    }

    return c_array;
  }

  private boolean[] clone(boolean[] k_array){
    return clone(k_array, 0);
  }

  public PolynomialGF2 plus(PolynomialGF2 polynom){

    boolean sumArray[];
    int lengthPoly0 = this.k_array.length;
    int lengthPoly1 = polynom.k_array.length;
    int lengthSmallArray;

    if(lengthPoly0 > lengthPoly1){
      lengthSmallArray = lengthPoly1;
      sumArray = clone(this.k_array);
    } else {
      lengthSmallArray = lengthPoly0;
      sumArray = clone(polynom.k_array);
    }

    for(int i = 0; i < lengthSmallArray; i++){
      if((this.k_array[i] ^ polynom.k_array[i] ) == true){
        sumArray[i] = true;
      } else {
        sumArray[i] = false;
      }
    }

    PolynomialGF2 sumPoly = new PolynomialGF2(sumArray);

    return sumPoly;

  }

  public PolynomialGF2 times(PolynomialGF2 polynom){
    /**

    ICH SCHREIBE DAS ARRAY VON VORNE NACH HINTEN !!!FALSCH!!!
    eS MUSS VON HINTEN NACH VORNE GESCHRIEBEN WERDEN
    FICKEREI EY


    */

    boolean proArray[];
    int lengthProArray;
    int countShift = 0;
    int smallerArrayLength;

    if(this.k_array.length > polynom.k_array.length){
      lengthProArray = this.k_array.length*2;
      smallerArrayLength = polynom.k_array.length;
    } else {
      lengthProArray = 2*polynom.k_array.length;
      smallerArrayLength = this.k_array.length;
    }

    proArray = new boolean[lengthProArray];

    //ENDE ARRAY ERZEUGUNG, START BEFÜLLEN


    for(int i = smallerArrayLength-1; 0 <= i; i--){
      if(polynom.k_array[i] == true){
        for(int k = this.k_array.length-1; 0 <= k; k--){
          proArray[k+i] = this.k_array[k];
        }
      } else {
        for(int k = this.k_array.length-1; 0 <= k; k--){
          proArray[k+i] = (this.k_array[k] || false);
        }
      }

    }

    //Polynom object mit ergebnis wird erzeugt
    PolynomialGF2 proPoly = new PolynomialGF2(proArray);

    return proPoly;

  }

  private int degree(PolynomialGF2 polynom1){
    int degree = polynom1.k_array.length-1;
    return degree;
  }

  private PolynomialGF2 shift(int k){

    boolean shiftedArray[];
    shiftedArray = clone(this.k_array,k);
    for(int i = 0; i < k; i++){
      shiftedArray[i] = false;
    }

    for(int i = 0; i < shiftedArray.length; i++){
      System.out.print(shiftedArray[i] + "\t");
    }
    System.out.print("\n");

    PolynomialGF2 shiftedPoly = new PolynomialGF2(shiftedArray);

    return shiftedPoly;
  }

  /*

  public PolynomialGF2 mod(PolynomialGF2 polynom1){


  }

  private boolean equals(boolean a, boolean b){


  }

  */



}
