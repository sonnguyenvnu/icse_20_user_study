public void unregisterAttachListener(View view){
  view.removeOnAttachStateChangeListener(this);
  if (childOnAttachStateChangeListener != null && view instanceof ViewGroup) {
    findDeepestChild((ViewGroup)view).removeOnAttachStateChangeListener(childOnAttachStateChangeListener);
  }
}
