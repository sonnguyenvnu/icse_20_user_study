public void apply(VectorForce value){
  Log.println("Applying " + value);
  x+=value.getX();
  y+=value.getY();
  if (moveObserver != null) {
    moveObserver.pointMoved(this);
  }
}
