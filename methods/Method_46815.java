@Override public void onCancelled(){
  mainFragment.reloadListElements(false,false,!mainFragment.IS_LIST);
  mainFragment.mSwipeRefreshLayout.setRefreshing(false);
}
