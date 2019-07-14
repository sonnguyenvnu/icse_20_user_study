@Override public boolean onFling(int velocityX,int velocityY){
  RecyclerView.LayoutManager layoutManager=mRecyclerView.getLayoutManager();
  if (layoutManager == null) {
    return false;
  }
  RecyclerView.Adapter adapter=mRecyclerView.getAdapter();
  if (adapter == null) {
    return false;
  }
  int minFlingVelocity=mRecyclerView.getMinFlingVelocity();
  return (Math.abs(velocityY) > minFlingVelocity || Math.abs(velocityX) > minFlingVelocity) && snapFromFling(layoutManager,velocityX,velocityY);
}
