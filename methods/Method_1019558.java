@Override protected double[] find(final long key){
  final int index=HashOperations.hashSearch(keys_,lgCurrentCapacity_,key);
  if (index == -1) {
    return null;
  }
  return Arrays.copyOfRange(values_,index * numValues_,(index + 1) * numValues_);
}
