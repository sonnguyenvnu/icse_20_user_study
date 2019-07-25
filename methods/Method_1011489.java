@Override public boolean equals(Object object){
  if (object instanceof AbstractConceptWrap) {
    return myPeer == ((AbstractConceptWrap)object).getPeer();
  }
  return false;
}
