@Override public ValueEnumeration elements(final int k){
  final int sz=this.set.size();
  if (sz >= 31 || k > (1 << 16)) {
    return new CoinTossingSubsetEnumerator(k);
  }
  return new SubsetEnumerator(k);
}
