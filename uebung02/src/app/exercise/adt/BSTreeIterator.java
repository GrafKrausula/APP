package app.exercise.adt;

import java.util.*;
import app.exercise.adt.*;
import app.exercise.algebra.*;



public class BSTreeIterator<E extends Comparable<E>> implements Iterator<E>{

    Knoten<E> cur;
    BSTree<E> Tree;

    public BSTreeIterator(BSTree<E> Tree){

        this.cur = Tree.root;
        this.Tree = Tree;
        //if(this.cur == null) return;
    }

  /*Alle weiteren erforderlichen, nicht implementierten Methoden lösen eine
    UnsupportedOperationException aus.
    Implementieren Sie die Methode Iterator<E> iterator() in BSTree, die
    ein passendes Objekt von BSTreeIterator zurückliefert. */

    public void leftest(Knoten<E> temp){
       while(temp.value != null){
         this.cur=temp;
         temp=temp.left;
       }
    }

    public Knoten<E> rightest(){
       Knoten<E> temp;
       temp = Tree.root;
       while(temp.value != null){
         temp=temp.right;
       }
       return temp.dad;
    }

    @Override
    public boolean hasNext(){
      return (rightest() != this.cur) ? true : false;
    }

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

          if((this.cur.left.value == null) && (this.cur.right.value == null)){
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

    @Override
    public void remove(){
      throw new UnsupportedOperationException();
    }
}
