@Override public Observable<Folder> create(final Folder folder){
  return Observable.create(new Observable.OnSubscribe<Folder>(){
    @Override public void call(    Subscriber<? super Folder> subscriber){
      folder.setCreatedAt(new Date());
      long result=mLiteOrm.save(folder);
      if (result > 0) {
        subscriber.onNext(folder);
      }
 else {
        subscriber.onError(new Exception("Create folder failed"));
      }
      subscriber.onCompleted();
    }
  }
);
}
