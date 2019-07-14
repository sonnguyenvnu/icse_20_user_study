@Override public void onNotifyAdapter(@Nullable List<Gist> items,int page){
  hideProgress();
  if (items == null || items.isEmpty()) {
    adapter.clear();
    return;
  }
  if (page <= 1) {
    adapter.insertItems(items);
  }
 else {
    adapter.addItems(items);
  }
}
