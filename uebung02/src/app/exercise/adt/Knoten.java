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

    /**
     * Erzeugt default Objekt und initialisiert Instanzvariablen mit null
     */

    public Knoten (){

    }

    /**
     * Erzeugt Wurzel eines Baums oder Subbaums nur mit value
     */

    public Knoten (T value){
      this.dad = null;
      this.value = value;
      this.left = new Knoten<T>();
      this.right = new Knoten<T>();
    }

    /**
     * Erzeugt Wurzel eines Baums oder Subbaums mit 3 parametern
     */

    public Knoten (T value, Knoten<T> left, Knoten<T> right){
      this.dad = null;
      this.value = value;
      this.left = left;
      this.right = right;
    }

    /**
     * Getter für den linken unterknoten dieses Knotens
     * @return den linken Knoten dieses Knotens
     */

    @Override
    public Knoten<T> getLeft(){
      return this.left;
    }

    /**
     * Getter für den rechten unterknoten dieses Knotens
     * @return den rechten Knoten dieses Knotens
     */

    @Override
    public Knoten<T> getRight(){
      return this.right;
    }

    /**
     * Methode zum abfragen ob dieses Objekt rot sei
     * @return "beliebigen boolean wert" in diesem fall false
     */

    @Override
    public boolean isRed(){
      return false;
    }

    /**
     * Methode für die rückgabe des Werts dieses Knotens
     * @return wert dieses Knotens
     */

    @Override
    public T getValue(){
      return this.value;
    }
}
