@Override protected void onBecomeFullyHidden(){
  if (undoView != null) {
    undoView.hide(true,0);
  }
}
