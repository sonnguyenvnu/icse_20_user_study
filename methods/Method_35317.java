@Override public Observable<List<Folder>> create(final List<Folder> folders){
  return Observable.create(new Observable.OnSubscribe<List<Folder>>(){
    @Override public void call(    Subscriber<? super List<Folder>> subscriber){
      Date now=new Date();
      for (      Folder folder : folders) {
        folder.setCreatedAt(now);
      }
      long result=mLiteOrm.save(folders);
      if (result > 0) {
        List<Folder> allNewFolders=mLiteOrm.query(QueryBuilder.create(Folder.class).appendOrderAscBy(Folder.COLUMN_NAME));
        subscriber.onNext(allNewFolders);
      }
 else {
        subscriber.onError(new Exception("Create folders failed"));
      }
      subscriber.onCompleted();
    }
  }
);
}
