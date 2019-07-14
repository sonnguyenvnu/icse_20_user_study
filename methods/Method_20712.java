@Override public Subscriber<? super Response<T>> call(final @NonNull Subscriber<? super T> subscriber){
  final Gson gson=this.gson;
  return new Subscriber<retrofit2.Response<T>>(){
    @Override public void onCompleted(){
      if (!subscriber.isUnsubscribed()) {
        subscriber.onCompleted();
      }
    }
    @Override public void onError(    final @NonNull Throwable e){
      if (!subscriber.isUnsubscribed()) {
        subscriber.onError(e);
      }
    }
    @Override public void onNext(    final @NonNull retrofit2.Response<T> response){
      if (subscriber.isUnsubscribed()) {
        return;
      }
      if (!response.isSuccessful()) {
        try {
          final ErrorEnvelope envelope=gson.fromJson(response.errorBody().string(),ErrorEnvelope.class);
          subscriber.onError(new ApiException(envelope,response));
        }
 catch (        final @NonNull IOException e) {
          subscriber.onError(new ResponseException(response));
        }
      }
 else {
        subscriber.onNext(response.body());
        subscriber.onCompleted();
      }
    }
  }
;
}
