@Override public void onPreExecute(String query){
  mainFragment.mSwipeRefreshLayout.setRefreshing(true);
  mainFragment.onSearchPreExecute(query);
}
