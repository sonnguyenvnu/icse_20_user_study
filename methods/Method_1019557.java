@Override protected void rebuild(final int newCapacity){
  final long[] oldKeys=keys_;
  final double[] oldValues=values_;
  keys_=new long[newCapacity];
  values_=new double[newCapacity * numValues_];
  count_=0;
  lgCurrentCapacity_=Integer.numberOfTrailingZeros(newCapacity);
  for (int i=0; i < oldKeys.length; i++) {
    if ((oldKeys[i] != 0) && (oldKeys[i] < theta_)) {
      insert(oldKeys[i],Arrays.copyOfRange(oldValues,i * numValues_,(i + 1) * numValues_));
    }
  }
  setRebuildThreshold();
}
