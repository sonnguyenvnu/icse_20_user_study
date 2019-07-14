private void initScrollHeight(){
  if (recyclerView.computeVerticalScrollOffset() == 0) {
    this.recyclerView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener(){
      @Override public boolean onPreDraw(){
        RecyclerViewFastScroller.this.recyclerView.getViewTreeObserver().removeOnPreDrawListener(this);
        iniHeight();
        return true;
      }
    }
);
  }
 else {
    iniHeight();
  }
}
