package app.exercise.adt;

/**
 * Eine Java-Klasse BSTree.
 *
 * @author Martin Krause
 */

import app.exercise.algebra.CompRational;
import java.util.AbstractCollection;
import java.util.*;
import app.*;

/**
 * E typisiert für generrischen Typ,
 * in diesem fall wird vorallem mit CompRational gearbeitet,
 * weswegen E mit Comparable extended werden muss.
 *
 */
public class BSTree<E extends Comparable<E>> extends AbstractCollection<E>{

   public Knoten<E> root;
   public Knoten<E> min;
   public Knoten<E> current;
   public char h = ' ';
   public Knoten<E> helpdad;

  /**
   * Erstellt ein BSTree Object mit der null wurzel und dem min aka leftest = null
   */
   public BSTree(){
      root = null; min = null;
   }
   /**
    * Erstellt ein BSTree Object mit der null wurzel und dem min aka leftest = null
    * @param cr Ein wurzelknoten wird aus dem generic object cr erzeugt und bildet die wurzel des Baums
    */
   public BSTree(E cr){
      this.root = new Knoten<E>(cr);
      this.current = this.root;
      System.out.println("BAUM mit root" + cr.toString() );
   }

   /**
    * Erstellt einen Iterator für diesen Baum
    */

   public Iterator<E> iterator(){
     BSTreeIterator<E> iterator = new BSTreeIterator(this);
     return iterator;
   }

   /**
    * Erstellt einen Iterator für diesen Baum
    * @return Ein Object array des Baums über den Iterator in order
    */

   @Override
   public Object[] toArray(){
     Object[] A = new Object[this.size()];
     int i = 0;
     for(Iterator<E> j = this.iterator(); j.hasNext();){
       A[i] = j.next();
       i++;
      }
      return A;

   }

   /**
    * Ermittelt die größe des Baums über einen iterator von this
    * @return die größe des Baums als Integer
    */

   @Override
   public int size(){
      int c = 0;
      E u;
      for(Iterator<E> j = this.iterator(); j.hasNext();){
  			u = j.next();
  			c++;
        System.out.println("SPIN ME RIGHT ROUND " + c + " " + u );
        if(c > 20) break;
  		}
      return c;
   }

   /**
    * Ermittelt ob wurzel vorhanden oder nicht
    * @return true wenn wurzel nicht gesetzt, false wenn wurzel vorhanden
    */

   @Override
   public boolean isEmpty(){
      return (this.root.value == null) ? false : true;
   }

   /**
    * Setzt min auf den linkesten/kleinsten wert des Baums
    */

   public void leftest(){
      Knoten<E> temp = root;
      while(temp.value != null){
        min=temp;
        temp=temp.left;
      }
   }

   /**
    * Findet den Vaterknoten eines bestimmten Werts durch findKnot
    * @param e Wert des Knoten dessen Vater gesucht wird
    */

   public Knoten<E> dad(E e){
     return findKnot(e, 'd');
   }

   /**Gibt null bei vorhandenem Knoten und den möglichen Insertknoten mit
    * value=null für weitere befüllungszwcke zurück. QryDad wird verwendet:
    * -Um mit 'd' den Vaterknoten für den jeweiligen wert zu erfragen.
    * -Um mit 'n' den nächstgrößeren Knoten zu erfragen
    * @param e Zu suchender Wert
    * @param qry Filter einstellung, 'd' für dad, 'k' für Knoten mit wert e, und ohne qry das Blatt fürs einfügen
    * @return je nach Filtereinstellung bei gefundenem Knoten null, Knoten(e) oder Blatt
    */


   public Knoten<E> findKnot(E e, char qry){
    Knoten<E> temp = root;
    Knoten<E> dad = root;


     while(true){ //bis temp der kleinste knoten mit null ist
       if(temp.value.compareTo(e) == 0){
         return (qry == 'd') ? dad : null; //returnd den nächstgrößeren value
       }

       if(temp.value.compareTo(e) == -1){

           dad = temp;
           temp = temp.right;

         System.out.println("RechtsDAD " + dad.value);
         System.out.println("Rechts    " + temp.value);
         h = 'r';
         this.helpdad = dad;
         if(temp.value == null) return temp;
       }

       if(temp.value.compareTo(e) == 1){

           dad = temp;
           temp = temp.left;

         System.out.println("LinksDAD " + dad.value);
         System.out.println("Links    " + temp.value);
         h = 'l';
         this.helpdad = dad;
         if(temp.value == null) return temp;
       }


     }


     //gibt den vater des kleinsten nullknotens oder jeweiligen knoten mit value null wieder für add
     //return (qry == 'k' && (e != root.value)) ? temp.dad : temp;
   }

   /**
    * Sucht das passende Blatt für im Baum womöglich neuen Wert
    * @param e zu suchender Wert
    * @return das passend Blatt für den einzusetzenden Wert e, oder null falls schon vorhanden
    */

   public Knoten<E> findKnot(E e){
     return findKnot(e, ' ');
   }

   /**
    * Nutzt die findknot mehode um den Suchbaum durchzugehen
    * @param o zu suchender knoten
    * @return true, falls Knoten schon enthalten; false, falls knoten nicht vorhanden
    */

   @Override
   public boolean contains(Object o){
     Knoten<E> saft = (Knoten<E>) o;
     return ((findKnot((E) saft.value) == null)) ? true : false; //findKnot gibt null bei vorhandenem Knoten zurück
   }



   @Override
   public boolean containsAll(Collection<?> c){
     //System.out.println("los gehts");
      for (Iterator<?> j = c.iterator(); j.hasNext();){
        if (! (this.contains(((Knoten) j.next()).value))) return false;
      }
      return true;
   }

   /**
    * Methode zum erzeugen eines Strings aus toArray(this)
    * @return den String mit den Stringdarstellungen des übergebenen Arrays
    */

   @Override
   public String toString(){
     String out = "";
     for(int i = 0; i < this.size(); i++){
       out += this.toArray()[i].toString() + " ";
     }
     return out;
   }

   /**
    * Fügt einen wert dem Suchbaum hinzu
    * @return true wenn adding erfolgreich, false wenn ein schon vorhanden
    */

   @Override
   public boolean add(E e){
     Knoten<E> temp;
     Knoten<E> dad;


     temp = findKnot(e); //sucht passendes Blatt für Wert e und setzt current darauf
     if (temp == null) return false; //null falls vorhanden, also kein einfügen

     temp = new Knoten<E>(e); //ersetzt Blatt durch neuen Knoten mit wert e
     this.current = temp;

     //dad = dad(e); //dad des neuen Knoten wird ermittelt
     this.current.dad = this.helpdad; //und dad im neuen knoten wird auf den richtigen dad gesetzt
     if(h == 'l') this.current.dad.left = this.current;
     if(h == 'r') this.current.dad.right = this.current;



     System.out.println("-----ADD------  " + current.value.toString());
     System.out.println("----ROOT------  " + root.value.toString() + root);

     System.out.println("-----DAD------  " + this.current.dad.value + this.current.dad);
     System.out.println("----CHILDREN-----  "  + this.current.dad.left.value + "-----" + this.current.dad.right.value);

    System.out.println("--ROOT--CHILDREN--  " + root.left.value + "-----" + root.right.value);

     System.out.println("::::::::::::::");

     //Könnte errorn, weil im dad keine referenz auf den Knoten besteht

     leftest(); //update den linkesten knoten

     return true;

   }




}
