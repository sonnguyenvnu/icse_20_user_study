@NonNull public static Disposable save(@android.support.annotation.Nullable List<Event> events,@android.support.annotation.Nullable String user){
  return RxHelper.getSingle(Single.fromPublisher(s -> {
    try {
      Login login=Login.getUser();
      if (login == null) {
        s.onNext("");
        s.onComplete();
        return;
      }
      BlockingEntityStore<Persistable> dataSource=App.getInstance().getDataStore().toBlocking();
      dataSource.delete(Event.class).where(Event.LOGIN.isNull().or(Event.LOGIN.eq(login.getLogin()))).get().value();
      if (events != null && !events.isEmpty() && TextUtils.equals(login.getLogin(),user)) {
        for (        Event event : events) {
          dataSource.delete(Event.class).where(Event.ID.eq(event.getId())).get().value();
          event.setLogin(login.getLogin());
          dataSource.insert(event);
        }
      }
      s.onNext("");
    }
 catch (    Exception e) {
      s.onError(e);
    }
    s.onComplete();
  }
)).subscribe(o -> {
  }
,Throwable::printStackTrace);
}
