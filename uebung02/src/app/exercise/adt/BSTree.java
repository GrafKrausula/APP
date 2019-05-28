package app.exercise.adt;

import app.exercise.algebra.CompRational;
import java.util.AbstractCollection;
import java.util.*;


//DELETED

public class BSTree<E extends Comparable<E>> extends AbstractCollection<E>{

   public Knoten<E> root;
   public Knoten<E> min;
   public Knoten<E> current;

   BSTree(){
      root = null; min = null;
   }

   BSTree(E cr){
      root = new Knoten<E>(cr);
      current = root;
   }


   public Iterator<E> iterator(){
     return new BSTreeIterator(this);
   }

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

   @Override
   public int size(){
      int c = 0;
      for(Iterator<E> j = this.iterator(); j.hasNext();){
  			j.next();
  			c++;
  		}
      return c;
   }

   @Override
   public boolean isEmpty(){
      return (this.root.value == null) ? false : true;
   }

   public void leftest(){
      Knoten<E> temp = root;
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
    Knoten<E> temp = root;
    Knoten<E> dad = root;


     while(temp.value != null){ //bis temp der kleinste knoten mit null ist

       if(temp.value.compareTo(e) == 0){
         return (qry == 'd') ? dad : null; //returnd den nächstgrößeren value
       }

       if(temp.value.compareTo(e) == 1){
         dad = temp;
         temp = temp.right;
       }

       if(temp.value.compareTo(e) == -1){
         dad = temp;
         temp = temp.left;
       }

     }

     return (qry == 'k' && (e != root.value)) ? temp.dad : temp; //gibt den vater des kleinsten nullknotens
      //gibt safe den jeweiligen knoten mit value null wieder
   }

   public Knoten<E> findKnot(E e){
     return findKnot(e, ' ');
   }


   public boolean contains(Knoten<E> o){
     return ((findKnot((E) o.value) == null)) ? true : false;
   }



   boolean remove(E cr){
     Knoten<E> temp;
     temp = findKnot(cr, 'k');

     if((temp.right == null) && (temp.left == null)){
       if(temp == temp.dad.right) temp.dad.right = null;
       if(temp == temp.dad.left) temp.dad.left = null;
       temp = new Knoten<E>();
     }



      return true;

   }

   private void killKnot(Knoten<E> temp){
     temp.dad = null;
     temp.value = null;
     temp.left = null;
     temp.right = null;
   }

   @Override
   public boolean containsAll(Collection<?> c){
     //System.out.println("los gehts");
      for (Iterator<?> j = c.iterator(); j.hasNext();){
        if (! (this.contains(((Knoten) j.next()).value))) return false;
      }
      return true;
   }


   @Override
   public String toString(){
     String out = "";
     for(int i = 0; i < this.size(); i++){
       out += this.toArray()[i].toString() + " ";
     }
     return out;
   }



   @Override
   public boolean add(E e){
     Knoten<E> temp;
     Knoten<E> dad;

     temp = findKnot(e);
     if (temp == null) return false;

     this.current = new Knoten<E>(e);

     dad = dad(e);
     current.dad = dad;

     leftest(); //update den linkesten knoten

     return true;

   }




}
