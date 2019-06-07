package app.exercise.adt;

/**
 * Eine Java-Klasse BSTreeIterator.
 *
 * @author Martin Krause
 */

import java.util.*;
import app.exercise.adt.*;
import app.exercise.algebra.*;



public class BSTreeIterator<E extends Comparable<E>> implements Iterator<E>{

    Knoten<E> cur;
    Knoten<E> root;
    BSTree<E> Tree;

    /**
     * Erzeugt ein Object BSTreeIterator, sprich einen Iterator für einen beliebigen Baum
     */

    public BSTreeIterator(){

    }

    public BSTreeIterator(BSTree<E> Tree){

        if (Tree == null) return;
        this.Tree = Tree;
        this.cur = Tree.root;
        this.root = Tree.root;
        System.out.println("ITERATOR START" + this.Tree.root.value.toString());

    }

  /**
   * Ermittel und setzt Current auf den linkesten Knoten eines Baums/Unterbaums
   * @param temp wurzel ab der gesucht werden soll
   */

    public void leftest(Knoten<E> temp){
       while(temp.value != null){
         this.cur=temp;
         temp=temp.left;
       }
    }


    /**
     * Ermittelt den rechtesten/also größten Knoten des Baums.
     * @return temp.dad der rechteste Knoten des ganze Baums
     */

    public Knoten<E> rightest(){
       Knoten<E> temp;
       Knoten<E> dad;
       temp = Tree.root; //suche wird ab wurzel gestartet
       while(true){ //er sucht bis er bei dem rechtesten Blatt angekommen ist
         dad = temp;
         temp=temp.right; //geht rechts
         if(temp.value == null) return dad;
       }
       //gibt den vater aus, da blatt mit null gefüllt und der vater der letzte knoten mit wer ist
       //return temp.dad;
    }

    /**
     * Ermittelt, ob der Iterator/Baum noch einen nächst(größer)en Knoten hat
     * @return false wenn current der rechteste Knoten, true wenn nicht.
     */

    @Override
    public boolean hasNext(){
      return (this.cur != rightest()) ? true : false;
    }

    /**
     * Bestimmt durch vergleichstruktur auf welchen wert this.current gesetzt wird.
     * dieser Wert entspricht dann dem nächsten Wert des Iterators.
     * @return den nächstgrößeren Wert des Baums
     */

    @Override
/*    public E next(){

        Knoten<E> last;

        if(!hasNext()) throw new NoSuchElementException();
        if(this.cur == Tree.root){this.cur = Tree.min; return this.cur.value;} //erster aufruf

        last = this.cur;

        while(true){

          if((this.cur.right.value != null) && (this.cur.right.value.compareTo(last.value) == 1)){
            this.cur = this.cur.right;
            leftest(this.cur);
          }

          if((this.cur.left.value == null) && (this.cur.right.value == null)){ //falls beides blätter
            if(this.cur.value.compareTo(last.value) > 0) return this.cur.value;
            if(this.cur.value.compareTo(last.value) <= 0){
               this.cur = this.cur.dad;
               return this.cur.value;
            }
          }

          while((this.cur.value.compareTo(last.value) <= 0) && (this.cur.right.value == null)){
            this.cur = this.cur.dad;
            if(this.cur.value.compareTo(last.value) > 0) return this.cur.value;
          }
        }
    } */

    public E next(){
    Knoten<E> last;
    int i = 0;

    if(!hasNext()) throw new NoSuchElementException();
    if(this.cur == Tree.root){this.cur = Tree.min; return this.cur.value;} //erster aufruf

    last = this.cur;


    System.out.println("CUR" + cur.value);
    System.out.println("VALUE LEFT " + cur.left.value);
    System.out.println("VALUE RIGHT " + cur.right.value);
    if(cur.right.value == null) i = 1;
    // If you can walk right, walk right, then fully left.
    // otherwise, walk up until you come from left.
    if(i != 1) {
        System.out.println("right " + cur.right.value);
        cur = cur.right;
        while (cur.left != null){
            if(cur.left == null) break;
            System.out.println("left " + cur.left.value);
            cur = cur.left;
        }
        return cur.value;
    }


    while(true) {
        if(cur.dad == null) {
            cur = null;
            return cur.value;
        }
        if(cur.dad.left == cur.dad) {
            cur = cur.dad;
           return cur.value;
        }
        cur = cur.dad;
     }
   }

    /**
     * Da remove nicht unterstützt werden soll,
     * wirft es eine UnsupportedOperationException
     */

    @Override
    public void remove(){
      throw new UnsupportedOperationException();
    }
}
