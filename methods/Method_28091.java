@Override public void onNotifyAdapter(@Nullable List<MilestoneModel> items){
  hideProgress();
  if (items == null || items.isEmpty()) {
    adapter.clear();
    return;
  }
  adapter.insertItems(items);
}
