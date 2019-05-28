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
    BSTree<E> Tree;

    /**
     * Erzeugt ein Object BSTreeIterator, sprich einen Iterator für einen beliebigen Baum
     */

    public BSTreeIterator(BSTree<E> Tree){

        this.cur = Tree.root;
        this.Tree = Tree;
        //if(this.cur == null) return;
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
       temp = Tree.root; //suche wird ab wurzel gestartet
       while(temp.value != null){ //er sucht bis er bei dem rechtesten Blatt angekommen ist
         temp=temp.right; //geht rechts
       }
       //gibt den vater aus, da blatt mit null gefüllt und der vater der letzte knoten mit wer ist
       return temp.dad;
    }

    /**
     * Ermittelt, ob der Iterator/Baum noch einen nächst(größer)en Knoten hat
     * @return false wenn current der rechteste Knoten, true wenn nicht.
     */

    @Override
    public boolean hasNext(){
      return (rightest() != this.cur) ? true : false;
    }

    /**
     * Bestimmt durch vergleichstruktur auf welchen wert this.current gesetzt wird.
     * dieser Wert entspricht dann dem nächsten Wert des Iterators.
     * @return den nächstgrößeren Wert des Baums
     */

    @Override
    public E next(){

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
