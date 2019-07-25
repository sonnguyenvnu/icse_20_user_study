@Override protected double[] find(final long key){
  final int index=HashOperations.hashSearch(mem_,lgCurrentCapacity_,key,ENTRIES_START);
  if (index == -1) {
    return null;
  }
  final double[] array=new double[numValues_];
  mem_.getDoubleArray(valuesOffset_ + ((long)SIZE_OF_VALUE_BYTES * numValues_ * index),array,0,numValues_);
  return array;
}
