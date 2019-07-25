@Override public void intercept(@NonNull UriRequest request,@NonNull UriCallback callback){
  Debugger.d("it worked");
  callback.onNext();
}
