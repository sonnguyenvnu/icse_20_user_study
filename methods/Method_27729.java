@Override public void onNotifyAdapter(@Nullable List<User> items){
  hideProgress();
  if (items == null || items.isEmpty()) {
    adapter.clear();
    return;
  }
  adapter.insertItems(items);
}
