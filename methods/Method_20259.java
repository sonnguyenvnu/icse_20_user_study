void handleFullImpressionVisible(EpoxyViewHolder epoxyHolder,boolean detachEvent){
  boolean previousFullyVisible=fullyVisible;
  fullyVisible=!detachEvent && isFullyVisible();
  if (fullyVisible != previousFullyVisible) {
    if (fullyVisible) {
      epoxyHolder.visibilityStateChanged(VisibilityState.FULL_IMPRESSION_VISIBLE);
    }
  }
}
