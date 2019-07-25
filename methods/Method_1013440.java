public ValueEnumeration elements(final int k){
  final List<Value> values=elements().all();
  return new SubsetEnumerator(k){
    @Override public Value nextElement(){
      if (!hasNext()) {
        return null;
      }
      return values.get(nextIndex());
    }
  }
;
}
