package app.exercise.adt;

import java.util.AbstractCollection;
import app.exercise.adt.*;
import app.exercise.algebra.*;


public class BSTreeIterator<E> implements Iterator<E>{

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

    @Override
    public void leftest(Knoten<E> temp){
       while(temp.value != null){
         this.cur=temp;
         temp=temp.left;
       }
    }

    public Knoten<E> rightest(){
       private Knoten<E> temp;
       temp = Tree.root;
       while(temp.value != null){
         temp=temp.right;
       }
       return temp.dad;
    }

    @Override
    public boolean hasNext(){
      (rightest() != this.cur) ? return true : return false;
    }

    @Override
    public E next(){

        private Knoten<E> last;

        if(!hasNext()) throw new NoSuchElementException();
        if(this.cur == Tree.root){this.cur = Tree.min; return this.cur;} //erster aufruf

        last = this.cur;

        while(true){

          if((this.cur.right.value != null) && (this.cur.right.value.compareTo(last.value) == 1)){
            this.cur = this.cur.right;
            leftest(this.cur);
          }

          if((this.cur.left.value == null) && (this.cur.right.value == null)){
            if(this.cur.value.compareTo(last.value) > 0) return this.cur;
            if(this.cur.value.compareTo(last.value) <= 0){
               this.cur = this.cur.dad;
               return this.cur;
            }
          }

          while((this.cur.value.compareTo(last.value) <= 0) && (this.cur.right.value == null)){
            this.cur = this.cur.dad;
            if(this.cur.value.compareTo(last.value) > 0) return this.cur;
          }


        }

        return null;

    }

    @Override
    public void forEachRemaining(Consumer<? super E> action){
      throw new UnsupportedOperationException();
    }

    @Override
    public void remove(){
      throw new UnsupportedOperationException();
    }
}
