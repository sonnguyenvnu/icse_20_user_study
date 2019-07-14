protected void iniHeight(){
  if (scrollerView.isSelected())   return;
  int verticalScrollOffset=RecyclerViewFastScroller.this.recyclerView.computeVerticalScrollOffset();
  int verticalScrollRange=RecyclerViewFastScroller.this.computeVerticalScrollRange();
  float proportion=(float)verticalScrollOffset / ((float)verticalScrollRange - height);
  setScrollerHeight(height * proportion);
}
