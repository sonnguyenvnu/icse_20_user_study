@Override public void intercept(@NonNull UriRequest request,@NonNull UriCallback callback){
  if (UriSourceTools.shouldHandle(request,false)) {
    callback.onNext();
  }
 else {
    callback.onComplete(UriResult.CODE_FORBIDDEN);
  }
}
