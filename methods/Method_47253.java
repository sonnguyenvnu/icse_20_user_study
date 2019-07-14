@Override public void onPageSelected(int position){
  if (isAttachedToWindow) {
    setSelectedPage(position);
  }
 else {
    setCurrentPageImmediate();
  }
}
