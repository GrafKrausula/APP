package app.exercise.adt;

import app.exercise.algebra.CompRational;
import app.exercise.visualtree.Knoten;
import java.util.AbstractCollection;
import java.util.Iterator;

//DELETED

public class BSTree<E> extends java.util.AbstractCollection<E>{

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
      current = root;
   }


   public Iterator<E> iterator(){
     return new BSTreeIterator(this);
   }

   @Override
   public Object[] toArray(){
     Object[] A = new Object[this.size];
     int i = 0;
     for(Iterator<Knoten> j = this.iterator(); j.hasNext();){
       A[i] j.next();
       i++;
      }
      return A;

   }

   @Override
   public int size(){
      int c = 0;
      for(Iterator<Knoten> j = this.iterator(); j.hasNext();){
  			j.next();
  			c++;
  		}
      return c;
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


     while(temp.value != null){ //bis temp der kleinste knoten mit null ist

       if(temp.value.compareTo(e) == 0){
         (qryDad == 'd') ? return dad : return null; //returnd den nächstgrößeren value
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

     (qry == 'k' && (e != root.value)) ? return temp.dad : return temp; //gibt den vater des kleinsten nullknotens
      //gibt safe den jeweiligen knoten mit value null wieder
   }

   public Knoten<E> findKnot(E e){
     return findKnot(e, ' ');
   }


   @Override
   public boolean contains(Object o){
     ((findKnot((E) o.value)) == null) ? return true : return false;
   }



   @Override
   boolean remove(E cr){
     temp = new Knoten<E>;
     temp = findKnot(cr, 'k');

     if((temp.right == null) && (temp.left == null)){
     (temp == temp.dad.right) temp.dad.right = null : temp.dad.left = null;
     temp = new Knoten<E>();
     }







   }

   private void killKnot(Knoten<E> temp){
     temp.dad == null;
     temp.value == null;
     temp.left == null;
     temp.right == null;
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
     for(int = 0; i < this.size(); i++){
       out += this.toArray()[i] + " ";
     }
     return out;
   }



   @Override
   public boolean add(E e){
     temp = new Knoten<E>;
     dad = new Knoten<E>;

     temp = findKnot(e);
     if (temp == null) return false;

     this.current = new Knoten<E>(e);

     dad = dad(e);
     current.dad = dad;

     leftest(); //update den linkesten knoten

     return true;

   }




}
