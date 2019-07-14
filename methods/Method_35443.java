@Override protected Subscription subscribeEvents(){
  return RxBus.getInstance().toObservable().observeOn(AndroidSchedulers.mainThread()).doOnNext(new Action1<Object>(){
    @Override public void call(    Object o){
      if (o instanceof PlayListCreatedEvent) {
        onPlayListCreatedEvent((PlayListCreatedEvent)o);
      }
 else       if (o instanceof FavoriteChangeEvent) {
        onFavoriteChangeEvent((FavoriteChangeEvent)o);
      }
 else       if (o instanceof PlayListUpdatedEvent) {
        onPlayListUpdatedEvent((PlayListUpdatedEvent)o);
      }
    }
  }
).subscribe(RxBus.defaultSubscriber());
}
