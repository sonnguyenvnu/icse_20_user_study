@Override public void onActivityCreated(Bundle savedInstanceState){
  super.onActivityCreated(savedInstanceState);
  mResource=onAttachResource();
  mSwipeRefreshLayout.setOnRefreshListener(this::onSwipeRefresh);
  mList.setItemAnimator(new NoChangeAnimationItemAnimator());
  mList.setLayoutManager(onCreateLayoutManager());
  mItemAdapter=onCreateAdapter();
  if (mResource.has()) {
    mItemAdapter.replace(mResource.get());
  }
  mAdapter=new LoadMoreAdapter(mItemAdapter);
  mList.setAdapter(mAdapter);
  onAttachScrollListener();
  updateRefreshing();
}
