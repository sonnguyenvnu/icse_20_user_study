public void rebuildAllFragmentViews(boolean last,boolean showLastAfter){
  if (transitionAnimationInProgress || startedTracking) {
    rebuildAfterAnimation=true;
    rebuildLastAfterAnimation=last;
    showLastAfterAnimation=showLastAfter;
    return;
  }
  int size=fragmentsStack.size();
  if (!last) {
    size--;
  }
  if (inPreviewMode) {
    size--;
  }
  for (int a=0; a < size; a++) {
    fragmentsStack.get(a).clearViews();
    fragmentsStack.get(a).setParentLayout(this);
  }
  if (delegate != null) {
    delegate.onRebuildAllFragments(this,last);
  }
  if (showLastAfter) {
    showLastFragment();
  }
}
