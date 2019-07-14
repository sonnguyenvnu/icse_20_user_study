private void setNextFocus(View view,@IdRes int nextId){
  view.setNextFocusForwardId(nextId);
  if (Build.VERSION.SDK_INT >= 22) {
    view.setAccessibilityTraversalBefore(nextId);
  }
}
