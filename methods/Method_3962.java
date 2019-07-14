void onExitLayoutOrScroll(boolean enableChangeEvents){
  mLayoutOrScrollCounter--;
  if (mLayoutOrScrollCounter < 1) {
    if (DEBUG && mLayoutOrScrollCounter < 0) {
      throw new IllegalStateException("layout or scroll counter cannot go below zero." + "Some calls are not matching" + exceptionLabel());
    }
    mLayoutOrScrollCounter=0;
    if (enableChangeEvents) {
      dispatchContentChangedIfNecessary();
      dispatchPendingImportantForAccessibilityChanges();
    }
  }
}
