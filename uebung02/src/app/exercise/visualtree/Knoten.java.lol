package app.exercise.visualtree;

import app.exercise.algebra.CompRational;

class Knoten<T> implements DrawableTreeElement<T>{
    protected Knoten<T> left;
    protected Knoten<T> right;
    T Knotenvalue; boolean isRot;
    static int size = 0;

    Knoten (){
      this.Knotenvalue = null;
      this.size = size++;
    }

    Knoten (T Knotenvalue){
      this.Knotenvalue = Knotenvalue;
      this.left = new Knoten ();
      this.right = new Knoten ();
      this. size = size++;
    }

    Knoten (T Knotenvalue, Knoten<T> left, Knoten<T> right){
      this.Knotenvalue = Knotenvalue;
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
      return this.Knotenvalue;
    }
}
