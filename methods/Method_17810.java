@Nullable InternalNode getCachedLayout(){
  return mThreadIdToLastMeasuredLayout == null ? null : mThreadIdToLastMeasuredLayout.get(Thread.currentThread().getId());
}
