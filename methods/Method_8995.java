public void invalidateViews(){
  int count=getChildCount();
  for (int a=0; a < count; a++) {
    getChildAt(a).invalidate();
  }
}
