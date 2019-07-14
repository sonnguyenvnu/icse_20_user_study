@Override public CloseableReference<T> clone(){
  Preconditions.checkState(isValid());
  return new RefCountCloseableReference<T>(mSharedReference,mLeakHandler,mStacktrace);
}
