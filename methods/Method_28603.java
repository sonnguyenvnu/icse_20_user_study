public int getCurrentItem(){
  try {
    RecyclerView.LayoutManager layoutManager=mRecyclerView.getLayoutManager();
    View view=mPagerSnapHelper.findSnapView(layoutManager);
    if (view != null)     return layoutManager.getPosition(view);
  }
 catch (  NullPointerException e) {
    e.printStackTrace();
  }
  return 0;
}
