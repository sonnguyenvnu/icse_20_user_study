private void updateRefreshing(){
  boolean loadingBroadcast=mResource.isLoadingBroadcast();
  boolean hasBroadcast=mResource.hasBroadcast();
  boolean loadingCommentList=mResource.isLoadingCommentList();
  mSwipeRefreshLayout.setRefreshing(loadingBroadcast && (mSwipeRefreshLayout.isRefreshing() || hasBroadcast));
  ViewUtils.setVisibleOrGone(mProgress,loadingBroadcast && !hasBroadcast);
  mAdapter.setLoading(hasBroadcast && loadingCommentList);
}
