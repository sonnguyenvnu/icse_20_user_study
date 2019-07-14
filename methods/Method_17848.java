boolean wasLayoutInterrupted(){
  return mLayoutStateFuture == null ? false : mLayoutStateFuture.isInterrupted();
}
