@Override public void handle(@NonNull UriRequest request,@NonNull UriCallback callback){
  mInitHelper.ensureInit();
  super.handle(request,callback);
}
