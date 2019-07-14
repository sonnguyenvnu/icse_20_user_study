@Subscribe(threadMode=ThreadMode.POSTING) public void onDoulistUpdated(DoulistUpdatedEvent event){
  if (event.isFromMyself(this) || isEmpty()) {
    return;
  }
  List<Doulist> doulistList=get();
  for (int i=0, size=doulistList.size(); i < size; ++i) {
    Doulist doulist=doulistList.get(i);
    if (doulist.id == event.doulist.id) {
      doulistList.set(i,event.doulist);
      getListener().onDoulistChanged(getRequestCode(),i,doulistList.get(i));
    }
  }
}
