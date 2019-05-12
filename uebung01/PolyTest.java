import java.util.*;
import java.lang.Math;

public class PolyTest{

  public static void main(String[] args){

    int n = Integer.parseInt(args[0]);

    PolynomialGF2 poly0 = new PolynomialGF2(randArray(n));
    PolynomialGF2 poly1 = new PolynomialGF2(randArray(n));
    //PolynomialGF2 schlong = new PolynomialGF2(array);

    System.out.print(poly0.toString() + poly1.toString());
    //System.out.println("Schlong:" + schlong.getHash());
    System.out.print("Summe: " + (poly0.plus(poly1)).toString());
    System.out.print("Produkt: " + (poly0.times(poly1)).toString());

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
    //    System.out.print(array[i] + "\t");
    }
    //  System.out.print("\n");
    return array;
  }
}
