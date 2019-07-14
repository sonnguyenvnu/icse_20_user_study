public static Disposable save(@NonNull List<Gist> models,@NonNull String ownerName){
  return RxHelper.getSingle(Single.fromPublisher(s -> {
    try {
      Login login=Login.getUser();
      if (login != null) {
        if (login.getLogin().equalsIgnoreCase(ownerName)) {
          BlockingEntityStore<Persistable> dataSource=App.getInstance().getDataStore().toBlocking();
          dataSource.delete(Gist.class).where(Gist.OWNER_NAME.equal(ownerName)).get().value();
          if (!models.isEmpty()) {
            for (            Gist gistModel : models) {
              dataSource.delete(Gist.class).where(ID.eq(gistModel.getId())).get().value();
              gistModel.setOwnerName(ownerName);
              dataSource.insert(gistModel);
            }
          }
        }
 else {
          App.getInstance().getDataStore().toBlocking().delete(Gist.class).where(Gist.OWNER_NAME.notEqual(ownerName).or(OWNER_NAME.isNull())).get().value();
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
