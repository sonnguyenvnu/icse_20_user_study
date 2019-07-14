@Override public void emptyView(boolean visible){
  emptyView.setVisibility(visible ? View.VISIBLE : View.GONE);
  fastScroller.setVisibility(visible ? View.GONE : View.VISIBLE);
}
