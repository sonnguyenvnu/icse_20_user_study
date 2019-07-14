public boolean isOnTop(){
  if (mHeaderViews == null || mHeaderViews.size() == 0) {
    return false;
  }
  View view=mHeaderViews.get(0);
  if (view.getParent() != null) {
    return true;
  }
 else {
    return false;
  }
}
