private void updateRefreshing(){
  boolean loading=mNotificationListResource.isLoading();
  boolean empty=mNotificationListResource.isEmpty();
  boolean loadingMore=mNotificationListResource.isLoadingMore();
  mSwipeRefreshLayout.setRefreshing(loading && (mSwipeRefreshLayout.isRefreshing() || !empty) && !loadingMore);
  ViewUtils.setVisibleOrGone(mProgress,loading && empty);
  mAdapter.setLoading(loading && !empty && loadingMore);
}
