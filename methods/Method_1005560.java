@Override public void intercept(@NonNull UriRequest request,@NonNull UriCallback callback){
  initIfNeeded();
  Uri uri=RouterUtils.appendParams(request.getUri(),mCommonParams);
  request.setUri(uri);
  callback.onNext();
}
