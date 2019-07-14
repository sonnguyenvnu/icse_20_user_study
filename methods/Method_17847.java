boolean wasLayoutCanceled(){
  return mLayoutStateFuture == null ? false : mLayoutStateFuture.isReleased();
}
