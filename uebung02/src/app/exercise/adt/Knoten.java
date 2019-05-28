package app.exercise.adt;

/**
 * Eine Java-Klasse Knoten.
 * Erzeugt ein Objekt Knoten
 *
 * @author Martin Krause
 */

import app.exercise.algebra.CompRational;
import app.exercise.visualtree.*;

public class Knoten< T extends Comparable<T> > implements DrawableTreeElement<T>{
    public Knoten<T> left;
    public Knoten<T> right;
    public Knoten<T> dad;
    T value; boolean isRot;
    static int size = 0;

    Knoten (){
      
    }

    Knoten (T value){
      this.dad = null;
      this.value = value;
      this.left = new Knoten<T>();
      this.right = new Knoten<T>();
    }

    Knoten (T value, Knoten<T> left, Knoten<T> right){
      this.dad = null;
      this.value = value;
      this.left = left;
      this.right = right;
    }

    @Override
    public Knoten<T> getLeft(){
      return this.left;
    }

    @Override
    public Knoten<T> getRight(){
      return this.right;
    }

    @Override
    public boolean isRed(){
      return false;
    }

    @Override
    public T getValue(){
      return this.value;
    }
}
