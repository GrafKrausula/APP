package app.exercise.visualtree;

import app.exercise.algebra.CompRational;

class Knoten<T> implements DrawableTreeElement<T>{
    public Knoten<T> left;
    public Knoten<T> right;
    public Knoten<T> dad;
    T value; boolean isRot;
    static int size = 0;

    Knoten (){
      this.dad = null;
      this.value = null;
      this.size = size++;
    }

    Knoten (T value){
      this.dad = null;
      this.value = value;
      this.left = new Knoten ();
      this.right = new Knoten ();
      this.size = size++;
    }

    Knoten (T value, Knoten<T> left, Knoten<T> right){
      this.dad = null;
      this.value = value;
      this.left = left;
      this.right = right;
      this.size = size++;
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
