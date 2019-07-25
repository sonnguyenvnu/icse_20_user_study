@Override public ValueEnumeration elements(final int k){
  if (needBigInteger()) {
    return getBigSubsetEnumerator(k);
  }
 else {
    return getSubsetEnumerator(k,size());
  }
}
