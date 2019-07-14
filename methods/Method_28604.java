public int getRealCurrentItem(){
  CBPageAdapter adapter=(CBPageAdapter)mRecyclerView.getAdapter();
  int count=adapter.getRealItemCount();
  return getCurrentItem() % count;
}
