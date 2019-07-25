@Override public ValueEnumeration elements(final int k){
  normalize();
  return new EnumerableValue.SubsetEnumerator(k){
    @Override public Value nextElement(){
      if (!hasNext()) {
        return null;
      }
      return elems.elementAt(nextIndex());
    }
  }
;
}
