public static Disposable saveForGist(@NonNull List<Comment> models,@NonNull String gistId){
  return RxHelper.getSingle(Single.fromPublisher(s -> {
    try {
      BlockingEntityStore<Persistable> dataSource=App.getInstance().getDataStore().toBlocking();
      dataSource.delete(Comment.class).where(GIST_ID.equal(gistId)).get().value();
      if (!models.isEmpty()) {
        for (        Comment model : models) {
          dataSource.delete(Comment.class).where(ID.eq(model.getId())).get().value();
          model.setGistId(gistId);
          dataSource.insert(model);
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
