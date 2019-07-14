private void initializeRecyclerView(final View layout){
  mRecyclerView=UI.findViewById(layout,R.id.recycler_view);
  LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
  layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
  layoutManager.scrollToPosition(0);
  mRecyclerView.setLayoutManager(layoutManager);
}
