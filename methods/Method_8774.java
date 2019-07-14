public void onSizeChanged(int paramInt1,int paramInt2,int paramInt3,int paramInt4){
  if (!shouldExpand) {
    post(PagerSlidingTabStrip.this::notifyDataSetChanged);
  }
}
