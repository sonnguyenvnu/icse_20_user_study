@Override protected void rebuild(final int newCapacity){
  final int numValues=getNumValues();
  checkIfEnoughMemory(mem_,newCapacity,numValues);
  final int currCapacity=getCurrentCapacity();
  final long[] keys=new long[currCapacity];
  final double[] values=new double[currCapacity * numValues];
  mem_.getLongArray(keysOffset_,keys,0,currCapacity);
  mem_.getDoubleArray(valuesOffset_,values,0,currCapacity * numValues);
  mem_.clear(keysOffset_,((long)SIZE_OF_KEY_BYTES * newCapacity) + ((long)SIZE_OF_VALUE_BYTES * newCapacity * numValues));
  mem_.putInt(RETAINED_ENTRIES_INT,0);
  mem_.putByte(LG_CUR_CAPACITY_BYTE,(byte)Integer.numberOfTrailingZeros(newCapacity));
  valuesOffset_=keysOffset_ + (SIZE_OF_KEY_BYTES * newCapacity);
  lgCurrentCapacity_=Integer.numberOfTrailingZeros(newCapacity);
  for (int i=0; i < keys.length; i++) {
    if ((keys[i] != 0) && (keys[i] < theta_)) {
      insert(keys[i],Arrays.copyOfRange(values,i * numValues,(i + 1) * numValues));
    }
  }
  setRebuildThreshold();
}
