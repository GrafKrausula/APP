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

    @Override
    public boolean hasNext(){
      return true;
    }

    @Override
    public E next(){

        private Knoten<E> last;

        if(!hasNext()) throw new NoSuchElementException();
        if(this.cur == Tree.root){this.cur = Tree.min; return this.cur;} //erster aufruf

        last = this.cur;

        while(true){

          if(this.cur.left.value == null && this.cur.right.value == null){
            if(this.cur.value.compareTo(last.value) > 0) return this.cur;
            if(this.cur.value.compareTo(last.value) == 0) this.cur = this.cur.dad;
          }

          if(this.cur.left.value.compareTo(last.value) == 1 && this.cur.right.value != null && this.cur.right.value.compareTo(last.value) == -1){
            this.cur = this.cur.right;
          }



        }




        /*

        if(next.right != null) {
            next = next.right;
            while (next.left != null)
                next = next.left;
            return r;
        }

        while(true) {
            if(next.parent == null) {
                next = null;
                return r;
            }
            if(next.parent.left == next) {
                next = next.parent;
               return r;
            }
            next = next.parent;
        }if(next.right != null) {
            next = next.right;
            while (next.left != null)
                next = next.left;
            return r;
        }

        while(true) {
            if(next.parent == null) {
                next = null;
                return r;
            }
            if(next.parent.left == next) {
                next = next.parent;
               return r;
            }
            next = next.parent;
        }
*/
        return this.cur.value;
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
