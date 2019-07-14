/** 
 * Calculates a LayoutState for the given LayoutStateFuture on the thread that calls this. 
 */
private @Nullable LayoutState calculateLayoutState(LayoutStateFuture layoutStateFuture){
  final LayoutState layoutState=layoutStateFuture.runAndGet();
synchronized (mLayoutStateFutureLock) {
    layoutStateFuture.unregisterForResponse();
    if (layoutStateFuture.getWaitingCount() == 0) {
      layoutStateFuture.release();
      mLayoutStateFutures.remove(layoutStateFuture);
    }
  }
  return layoutState;
}
