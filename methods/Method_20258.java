void handleFocus(EpoxyViewHolder epoxyHolder,boolean detachEvent){
  boolean previousFocusedVisible=focusedVisible;
  focusedVisible=!detachEvent && isInFocusVisible();
  if (focusedVisible != previousFocusedVisible) {
    if (focusedVisible) {
      epoxyHolder.visibilityStateChanged(VisibilityState.FOCUSED_VISIBLE);
    }
 else {
      epoxyHolder.visibilityStateChanged(VisibilityState.UNFOCUSED_VISIBLE);
    }
  }
}
