@Override protected Subscription subscribeEvents(){
  return RxBus.getInstance().toObservable().observeOn(AndroidSchedulers.mainThread()).doOnNext(new Action1<Object>(){
    @Override public void call(    Object o){
      if (o instanceof PlayListUpdatedEvent) {
        mPresenter.loadLocalMusic();
      }
    }
  }
).subscribe();
}
