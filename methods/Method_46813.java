@Override public void onPostExecute(String query){
  mainFragment.onSearchCompleted(query);
  mainFragment.mSwipeRefreshLayout.setRefreshing(false);
}
