@Override public void onNotifyAdapter(@Nullable List<TimelineModel> items,int page){
  hideProgress();
  if (items == null) {
    adapter.subList(1,adapter.getItemCount());
    return;
  }
  if (page == 1) {
    adapter.subList(1,adapter.getItemCount());
  }
  adapter.addItems(items);
}
