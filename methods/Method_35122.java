@Override public void performChange(@NonNull ViewGroup container,@Nullable View from,@Nullable View to,boolean isPush,@NonNull ControllerChangeCompletedListener changeListener){
  if (!canceled) {
    if (from != null && (!isPush || removesFromViewOnPush)) {
      container.removeView(from);
    }
    if (to != null && to.getParent() == null) {
      container.addView(to);
    }
  }
  if (container.getWindowToken() != null) {
    changeListener.onChangeCompleted();
  }
 else {
    this.changeListener=changeListener;
    this.container=container;
    container.addOnAttachStateChangeListener(this);
  }
}
