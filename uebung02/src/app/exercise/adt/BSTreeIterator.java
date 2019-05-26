package app.exercise.adt;

import java.util.AbstractCollection;
import app.exercise.adt.*;
import app.exercise.algebra.*;


public class BSTreeIterator<E> implements Iterator<E>{

    Knoten<E> cur;
    Knoten<E> lst;
    BSTree<E> Tree;

    public BSTreeIterator(BSTree<E> Tree){

        this.cur = Tree.root;
        this.Tree = Tree;
        if(this.cur == null) return;
    }

  /*Alle weiteren erforderlichen, nicht implementierten Methoden lösen eine
    UnsupportedOperationException aus.
    Implementieren Sie die Methode Iterator<E> iterator() in BSTree, die
    ein passendes Objekt von BSTreeIterator zurückliefert. */

    public boolean hasNext(){

    }

    public E next(){
        if(!hasNext()) throw new NoSuchElementException();
        if(this.cur == Tree.root){this.cur = Tree.min; return this.cur;}


        return this.cur.value;
    }


    public void forEachRemaining(Consumer<? super E> action){
      throw new UnsupportedOperationException();
    }

    public void remove(){
      throw new UnsupportedOperationException();
    }
}
