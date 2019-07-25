/** 
 * Converts the current state of the sketch into a compact sketch
 * @return compact sketch
 */
@SuppressWarnings("unchecked") public CompactSketch<S> compact(){
  if (getRetainedEntries() == 0) {
    return new CompactSketch<>(null,null,theta_,isEmpty_);
  }
  final long[] keys=new long[getRetainedEntries()];
  final S[] summaries=(S[])Array.newInstance(summaries_.getClass().getComponentType(),getRetainedEntries());
  int i=0;
  for (int j=0; j < keys_.length; j++) {
    if (summaries_[j] != null) {
      keys[i]=keys_[j];
      summaries[i]=(S)summaries_[j].copy();
      i++;
    }
  }
  return new CompactSketch<>(keys,summaries,theta_,isEmpty_);
}
