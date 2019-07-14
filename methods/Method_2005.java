private void initializeGridRecyclerView(final View layout){
  mRecyclerView=UI.findViewById(layout,R.id.recycler_view);
  GridLayoutManager layoutManager=new GridLayoutManager(getContext(),mConfig.gridSpanCount);
  layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
  layoutManager.scrollToPosition(0);
  mRecyclerView.setLayoutManager(layoutManager);
}
