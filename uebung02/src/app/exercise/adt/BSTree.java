package app.exercise.adt;

import app.exercise.algebra.CompRational;
import app.exercise.visualtree.Knoten;
import java.util.AbstractCollection;
import java.util.Iterator;

//DELETED

public class BSTree<E> extends java.util.AbstractCollection<E>{

   public int knots = 0;
   public Knoten<E> root;
   public Knoten<E> min;
   public Knoten<E> current;

   BSTree(){
      super();
      root = null; min = null;
   }

   BSTree(E cr){
      super();
      root = new Knoten<E>(cr);
      current = root
      knots++;
   }

   /*
   public Iterator<E> iterator(){
     return BSTreeIterator();
   }

   @Override
   public Object[] toArray(){

   }

   */

   @Override
   public int size(){
      return this.knots;
   }

   @Override
   public boolean isEmpty(){
      (this.root == null) ? return false : return true;
   }

   public void leftest(){
      private Knoten<E> temp = root;
      while(temp.value != null){
        min=temp;
        temp=temp.left;
      }
   }


   public Knoten<E> dad(E e){
     return findKnot(e, 'd');
   }

   /**Gibt null bei vorhandenem Knoten und den möglichen Insertknoten mit
    * value=null für weitere befüllungszwcke zurück. QryDad wird verwendet:
    * -Um mit 'd' den Vaterknoten für den jeweiligen wert zu erfragen.
    * -Um mit 'n' den nächstgrößeren Knoten zu erfragen               */


   public Knoten<E> findKnot(E e, char qry){
     private Knoten<E> temp = root;
     private Knoten<E> dad;

     if(qry == 'n' && (e != root.value)) temp = dad(e); //

     while(temp.value != null){ //bis temp der kleinste knoten mit null ist

       if(temp.value == e){
         (qryDad == 'd') ? return dad : return null; //returnd den dad des gesuchten knotens
       }

       if(temp.value > e){
         dad = temp;
         temp = temp.right;
       }

       if(temp.value < e){
         dad = temp;
         temp = temp.left;
       }

     }

     return temp; //gibt safe den jeweiligen knoten mit value null wieder
   }

   public Knoten<E> findKnot(E e){
     return findKnot(e, ' ');
   }


   @Override
   public boolean contains(Object o){
     ((findKnot((E) o.value)) == null) ? return true : return false;
   }

   /*

   @Override
   boolean remove(E cr){

   }

   @Override
   public boolean containsAll(Collection<?> c){

   }


   @Override
   public String toString(){

   }

   */

   @Override
   public boolean add(E e){

     findKnot(e).value = e;
     leftest();
     return true;

   }




}
