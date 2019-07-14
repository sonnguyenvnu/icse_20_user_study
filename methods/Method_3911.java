private boolean isForwardFling(RecyclerView.LayoutManager layoutManager,int velocityX,int velocityY){
  if (layoutManager.canScrollHorizontally()) {
    return velocityX > 0;
  }
 else {
    return velocityY > 0;
  }
}
