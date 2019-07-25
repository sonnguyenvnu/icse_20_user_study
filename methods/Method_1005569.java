@Override public void intercept(@NonNull UriRequest request,@NonNull UriCallback callback){
  next(mInterceptors.iterator(),request,callback);
}
