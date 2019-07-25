public Observable<PermissionResult> request(final List<String> permissions){
  return Observable.create(new ObservableOnSubscribe<PermissionResult>(){
    @Override public void subscribe(    final ObservableEmitter<PermissionResult> emitter) throws Exception {
      runtimePermission.request(permissions).onResponse(new ResponseCallback(){
        @Override public void onResponse(        PermissionResult result){
          if (result.isAccepted()) {
            emitter.onNext(result);
            emitter.onComplete();
          }
 else {
            emitter.onError(new Error(result));
          }
        }
      }
).ask();
    }
  }
);
}
