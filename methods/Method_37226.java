@Override public void postUnBindView(BaseCell cell){
  if (lSCell == null) {
    return;
  }
  totalDistance=0;
  if (lSCell.hasIndicator) {
    indicator.setTranslationX(0);
  }
  recyclerView.removeOnScrollListener(onScrollListener);
  recyclerView.setAdapter(null);
  lSCell=null;
  recycleView(cell);
}
