private void setRecyclerViewPosition(float relativePos){
  if (recyclerView != null) {
    int itemCount=recyclerView.getAdapter().getItemCount();
    int targetPos=(int)Utils.clamp(0,itemCount - 1,(int)(relativePos * (float)itemCount));
    recyclerView.smoothScrollToPosition(targetPos);
  }
}
