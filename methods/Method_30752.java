private void updateRefreshing(){
  boolean loading=mResource.isLoading();
  boolean empty=mResource.isEmpty();
  boolean loadingMore=mResource.isLoadingMore();
  mSwipeRefreshLayout.setRefreshing(loading && (mSwipeRefreshLayout.isRefreshing() || !empty) && !loadingMore);
  ViewUtils.setVisibleOrGone(mProgress,loading && empty);
  mAdapter.setLoading(loading && !empty && loadingMore);
}
