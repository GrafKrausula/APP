import java.io.*;
import java.lang.Math;
public class PolynomialGF2{


  private boolean k_array[];
  //private boolean copyk_array[];
  private int id;
  private int degree;
  private int n;
  private int hash;
  private String polynom;
  private boolean trimOn = true;

  static int nextID = 0;
  static int updateNextID() {
    return nextID++;
  }

  public PolynomialGF2(){
    this.k_array = new boolean[1];
    this.k_array[0] = true;
    //Default Konstruktor der 1 zurückliefern soll
  }

  public PolynomialGF2(boolean[] k_array){
    //if k_array isZero && isOne == false mache weiter
    this.id = nextID++;
    this.k_array = clone(trim(k_array));
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
      polyString += this.k_array[i] + "\t";
    }
    polyString += "\nDEGREE: " + this.degree + "\n" + "Hash: " + hash + "\n\n";

    return polyString;
  }

  private boolean[] trim(boolean[] bigArray){
    boolean trimmedArray[];

    if( (isZero(bigArray) == false) && (isOne(bigArray) == false) && (trimOn == true) ){
      for(int i = 0; i < bigArray.length; i++){
        if(bigArray[i] == true){
          trimmedArray = new boolean[bigArray.length-i];
          for(int k = 0+i; k < bigArray.length; k++){
            trimmedArray[k-i] = bigArray[k];
          }
          //System.out.println("-----Polynom" + this.id + " trimmed-----");
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

    if(opt_wrte_strt < 0){
      for(int k = 0; k < c_array.length; k++){
        c_array[c_array.length - 1 - k] = k_array[length-1+opt_wrte_strt-k];
      }

    } else {
      for(int k = 0; k < length; k++){
        c_array[c_array.length -1 - k - opt_wrte_strt] = k_array[length-1-k];
      }
    }

    return c_array;
  }

  private boolean[] clone(boolean[] k_array){
    return clone(k_array, 0);
  }

  public PolynomialGF2 plus(PolynomialGF2 polynom){

    /**

    */
    boolean sumArray[];
    boolean smallerArray[];
    boolean biggerArray[];
    int lengthPoly0 = this.k_array.length;
    int lengthPoly1 = polynom.k_array.length;
    int lengthSmallArray;
    int lengthBigArray;
    int shiftStart;

    if(lengthPoly0 > lengthPoly1){
      lengthSmallArray = lengthPoly1;
      lengthBigArray = lengthPoly0;
      smallerArray = polynom.k_array;
      biggerArray = this.k_array;
      sumArray = clone(this.k_array);
    } else {
      lengthSmallArray = lengthPoly0;
      lengthBigArray = lengthPoly1;
      smallerArray = this.k_array;
      biggerArray = polynom.k_array;
      sumArray = clone(polynom.k_array);
    }

    shiftStart = lengthBigArray - lengthSmallArray;

    for(int i = 0; i < shiftStart; i++){
      sumArray[i] = (biggerArray[i] ^ false);
    }

    for(int i = 0; i < lengthSmallArray; i++){
      if((smallerArray[i] ^ biggerArray[i + shiftStart] ) == true){
        sumArray[i + shiftStart] = true;
      } else {
        sumArray[i + shiftStart] = false;
      }
    }

    PolynomialGF2 sumPoly = new PolynomialGF2(sumArray);

    return sumPoly;

  }

  public PolynomialGF2 times(PolynomialGF2 polynom){
    /**

    */

    boolean proArray[];
    int lengthProArray;
    int countShift = 0;
    int smallerArrayLength;
    int bigAL_minusone;
    boolean smallerArray[];
    boolean biggerArray[];

    if(this.k_array.length > polynom.k_array.length){
      biggerArray = this.k_array;
      smallerArray = polynom.k_array;
      smallerArrayLength = smallerArray.length;
    } else {
      biggerArray = polynom.k_array;
      smallerArray = this.k_array;
      smallerArrayLength = smallerArray.length;
    }

    lengthProArray = biggerArray.length + smallerArrayLength-1;
    proArray = new boolean[lengthProArray];

    //ENDE ARRAY ERZEUGUNG, START BEFÜLLEN
    /**

    */
    for(int i = 0; i < smallerArrayLength; i++){
      if(smallerArray[smallerArrayLength-1-i] == true){
        for(int k = 0; k < biggerArray.length; k++){
          proArray[proArray.length-i-k-1] = ( biggerArray[biggerArray.length-1-k] || proArray[proArray.length-i-k-1]);
        }
        //debugBoolArray(proArray);
      } else {
        for(int k = 0; k < biggerArray.length; k++){
          proArray[proArray.length-i-k-1] = (proArray[proArray.length-i-k-1] || false);
        }
        //debugBoolArray(proArray);
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
      shiftedArray[shiftedArray.length-1-i] = false;
    }

    PolynomialGF2 shiftedPoly = new PolynomialGF2(shiftedArray);

    return shiftedPoly;
  }

  public PolynomialGF2 mod(PolynomialGF2 polynom){


    //divident : divisor

    PolynomialGF2 divident = (this);
    PolynomialGF2 divisor = polynom;
    PolynomialGF2 remainder;
    PolynomialGF2 temp;

    boolean falseArray[]; //= {false,false};
    boolean dividentArray[] = this.k_array;
    boolean divisorArray[] = polynom.k_array;
    boolean remainderArray[];
    int lengthPoly0 = this.k_array.length;
    int lengthPoly1 = polynom.k_array.length;
    int shiftStart = divisor.degree;
    int i = 0;
    String lmfao = "";

    divident = divident.shift(divisor.degree);
    divisor = divisor.shift(divident.degree-divisor.degree);

    falseArray = new boolean[divisor.degree + 1];
    PolynomialGF2 falsePoly = new PolynomialGF2(falseArray);

    while(divident.degree >= shiftStart){

      debugPoly("Divident" + i + ":"  + lmfao + format(divisor.degree-divident.degree), divident);
      if((divident.k_array[0] == true) && (divident.degree == divisor.degree)){
      //System.out.print("Summe:\n" + (divident.plus(divisor)).toString());
        divident = divident.plus(divisor);
        debugPoly("Divisor " + i + ":" +lmfao, divisor);
      } else {
        divident = divident.plus(falsePoly);
        debugPoly("Divisor " + i + ":" + lmfao, falsePoly);
      }

      divisor = divisor.shift(-1);
      falsePoly = falsePoly.shift(-1);
      i++;
      lmfao += " ";

    }

    remainder = divisor; //NUR ZUM COMPILEN
    return remainder;


  }
  /*
  private boolean equals(Object poly0){


  }

  */
  private void debugBoolArray(String info , boolean[] array){
    String ausgabe = "Debug" + info +": ";
    for(int i = 0; i < array.length; i++){
      ausgabe += array[i] + "\t";
    }
    System.out.print(ausgabe + "\n");
  }

  private void debugBoolArray(boolean[] array){
    debugBoolArray("", array);
  }

  private void debugPoly(String info , PolynomialGF2 poly){
    String ausgabe = "" + info;
    for(int i = 0; i < poly.k_array.length; i++){
      if( poly.k_array[i] == true){
      ausgabe += "1";
    } else {
      ausgabe += "0";
    }
    }
    System.out.print(ausgabe + "\n");
  }

  private void debugPoly(PolynomialGF2 poly){
    debugPoly("", poly);
  }

  private String format(int i){
    String space = "";
    for(int k = 0; k < i; k++){
      space += "0";
    }
    return space;
  }
}
