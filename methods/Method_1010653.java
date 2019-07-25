@Override public void clear(boolean clearDiff){
  super.clear(clearDiff);
  if (myInequalitySystem != null) {
    SNode hole=myHole;
    disposeHole();
    initHole(hole);
  }
}
