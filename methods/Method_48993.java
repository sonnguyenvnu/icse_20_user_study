@Override public boolean isLoop(){
  return getArity() == 2 && getVertex(0).equals(getVertex(1));
}
