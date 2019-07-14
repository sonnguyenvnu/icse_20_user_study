@Override public void onNotifyAdapter(@Nullable List<PinnedRepos> items){
  refresh.setRefreshing(false);
  stateLayout.hideProgress();
  if (items != null)   adapter.insertItems(items);
 else   adapter.clear();
}
