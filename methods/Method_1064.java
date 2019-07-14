@Override public void onViewportVisibilityHint(boolean isVisibleInViewportHint){
  final ControllerViewportVisibilityListener listener=mControllerViewportVisibilityListener;
  if (listener != null) {
    if (isVisibleInViewportHint && !mIsVisibleInViewportHint) {
      listener.onDraweeViewportEntry(mId);
    }
 else     if (!isVisibleInViewportHint && mIsVisibleInViewportHint) {
      listener.onDraweeViewportExit(mId);
    }
  }
  mIsVisibleInViewportHint=isVisibleInViewportHint;
}
