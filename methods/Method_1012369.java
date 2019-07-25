private void move(int dy){
  if (dy != 0) {
    int height=mRefreshHeaderContainer.getMeasuredHeight() + dy;
    setRefreshHeaderContainerHeight(height);
    mRefreshTrigger.onMove(false,false,height);
  }
}
