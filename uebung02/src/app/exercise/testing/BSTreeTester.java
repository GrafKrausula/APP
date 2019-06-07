package app.exercise.testing;
import app.exercise.adt.*;
import app.exercise.algebra.*;
import java.util.*;
public class BSTreeTester{
    public static void main(String[] args){
        if(args.length%2!=0){
            System.out.println("Ungerade Anzahl Argumente");
            return;
        }
        CompRational[] rtnls=new CompRational[args.length/2];
        for(int i=0;i<args.length;i=i+2){
            try{
                rtnls[i/2]=new CompRational(Integer.parseInt(args[i]),Integer.parseInt(args[i+1]));
            }catch(NumberFormatException e){
                System.out.println("Argument Nr. "+i+" ist kein integer");
                return;
            }
        }
        BSTree komplett=new BSTree(rtnls[0]);
        //BSTree gerade=new BSTree(rtnls[0]);
        //BSTree ungerade=new BSTree(rtnls[1]);
        System.out.println(rtnls.length);
        for(int i=1;i<rtnls.length;i++){
            komplett.add(rtnls[i]);
            if(i%2==0){
                //gerade.add(rtnls[i]);
            }else{
                //ungerade.add(rtnls[i]);
            }
        }
        System.out.println(komplett.toString());
        /*System.out.println(gerade.toString());
        System.out.println(ungerade.toString());
        System.out.print("Enthält sub Baum 1: ");
        System.out.println(komplett.containsAll(gerade));
        System.out.print("Enthält sub Baum 2: ");
        System.out.println(komplett.containsAll(ungerade));
        System.out.print("Enthält erste Rationale Zahl: ");
        System.out.println(komplett.contains(rtnls[0]));
        System.out.print("Enthält letzte Rationale Zahl: ");
        System.out.println(komplett.contains(rtnls[rtnls.length-1])); */




    }
}
