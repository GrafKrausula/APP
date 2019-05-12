import java.util.*;
import java.lang.Math;

public class PolyTest{

  public static void main(String[] args){

    int n = Integer.parseInt(args[0]);
    boolean array[] = new boolean[n];

    array = randArray(n);
    PolynomialGF2 schleck = new PolynomialGF2(array);
    //PolynomialGF2 schlong = new PolynomialGF2(array);

    System.out.println("Schleck:" + schleck.getHash());
    //System.out.println("Schlong:" + schlong.getHash());

  }

  private static boolean[] randArray(int n){
    boolean array[] = new boolean[n];
    boolean randBool;
    int randInt;

    for(int i = 0; i < n; i++){
      randInt = (int) Math.round( Math.random());
      if(randInt >= 1){
        randBool = true;
      } else {
        randBool = false;
      }

      array[i] = randBool;
      System.out.print(array[i] + "\t");
    }
    System.out.print("\n");
    return array;
  }
}
