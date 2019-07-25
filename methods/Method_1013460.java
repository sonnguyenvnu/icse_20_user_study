@Override public ValueEnumeration elements(final int kOutOfN){
  return new EnumerableValue.SubsetEnumerator(kOutOfN){
    @Override public Value nextElement(){
      if (!hasNext()) {
        return null;
      }
      return IntValue.gen(low + nextIndex());
    }
  }
;
}
