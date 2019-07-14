@Override public CloseableReference<T> clone(){
  Preconditions.checkState(isValid());
  return new DefaultCloseableReference<T>(mSharedReference,mLeakHandler,mStacktrace);
}
