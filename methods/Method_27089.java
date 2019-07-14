public static Disposable markAsRead(long id){
  return RxHelper.getSingle(Single.fromPublisher(s -> {
    try {
      BlockingEntityStore<Persistable> dataStore=App.getInstance().getDataStore().toBlocking();
      Notification current=dataStore.select(Notification.class).where(Notification.ID.eq(id)).get().firstOrNull();
      if (current != null) {
        current.setUnread(false);
        dataStore.update(current);
      }
      s.onNext(true);
    }
 catch (    Exception e) {
      e.printStackTrace();
      s.onError(e);
    }
    s.onComplete();
  }
)).subscribe(o -> {
  }
,Throwable::printStackTrace);
}
