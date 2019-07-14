private void updateCount(){
  if (iconSize > 1) {
    int spanCount=Math.max(1,getWidth() / iconSize);
    if (spanCount < 1) {
      spanCount=1;
    }
    this.setSpanCount(spanCount);
  }
}
