@Override protected Subscription subscribeEvents(){
  return RxBus.getInstance().toObservable().observeOn(AndroidSchedulers.mainThread()).doOnNext(new Action1<Object>(){
    @Override public void call(    Object o){
      if (o instanceof AddFolderEvent) {
        onAddFolders((AddFolderEvent)o);
      }
    }
  }
).subscribe(RxBus.defaultSubscriber());
}
