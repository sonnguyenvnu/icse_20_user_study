public void reset(){
  int childCount=containerLayout.getChildCount();
  while (--childCount > 0) {
    containerLayout.removeViewAt(childCount);
  }
  hideOnScroll=true;
}
