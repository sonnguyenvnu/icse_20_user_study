void handleVisible(@NonNull EpoxyViewHolder epoxyHolder,boolean detachEvent){
  boolean previousVisible=visible;
  visible=!detachEvent && isVisible();
  if (visible != previousVisible) {
    if (visible) {
      epoxyHolder.visibilityStateChanged(VisibilityState.VISIBLE);
    }
 else {
      epoxyHolder.visibilityStateChanged(VisibilityState.INVISIBLE);
    }
  }
}
