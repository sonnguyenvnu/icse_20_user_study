@SuppressWarnings({"unchecked"}) private void rebuild(final int newSize){
  final long[] oldKeys=keys_;
  final S[] oldSummaries=summaries_;
  keys_=new long[newSize];
  summaries_=(S[])Array.newInstance(oldSummaries.getClass().getComponentType(),newSize);
  lgCurrentCapacity_=Integer.numberOfTrailingZeros(newSize);
  count_=0;
  for (int i=0; i < oldKeys.length; i++) {
    if ((oldSummaries[i] != null) && (oldKeys[i] < theta_)) {
      insert(oldKeys[i],oldSummaries[i]);
    }
  }
  setRebuildThreshold();
}
