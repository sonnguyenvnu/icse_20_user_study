@Override public void onNotifyAdapter(@Nullable List<CommitFileChanges> items){
  hideProgress();
  if (items != null) {
    adapter.insertItems(items);
  }
}
