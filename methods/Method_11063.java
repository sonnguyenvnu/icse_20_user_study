@Override public void setVisibility(int visibility){
  super.setVisibility(visibility);
  if (visibility == View.VISIBLE) {
    startLoading(200);
  }
 else {
    stopLoading();
  }
}
