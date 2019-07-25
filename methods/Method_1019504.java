@Override public boolean propagate(final AtomicBoolean localPropagationInProgress,final Sketch sketchIn,final long singleHash){
  final long epoch=epoch_;
  if ((singleHash != NOT_SINGLE_HASH) && (getRetainedEntries(false) < exactLimit_)) {
    if (!startEagerPropagation()) {
      endPropagation(localPropagationInProgress,true);
      return false;
    }
    if (!validateEpoch(epoch)) {
      endPropagation(null,true);
      return true;
    }
    propagate(singleHash);
    endPropagation(localPropagationInProgress,true);
    return true;
  }
  final ConcurrentBackgroundThetaPropagation job=new ConcurrentBackgroundThetaPropagation(this,localPropagationInProgress,sketchIn,singleHash,epoch);
  executorService_.execute(job);
  return true;
}
