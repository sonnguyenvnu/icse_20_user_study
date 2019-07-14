@Override public Observable<Song> update(final Song song){
  return Observable.create(new Observable.OnSubscribe<Song>(){
    @Override public void call(    Subscriber<? super Song> subscriber){
      int result=mLiteOrm.update(song);
      if (result > 0) {
        subscriber.onNext(song);
      }
 else {
        subscriber.onError(new Exception("Update song failed"));
      }
      subscriber.onCompleted();
    }
  }
);
}
