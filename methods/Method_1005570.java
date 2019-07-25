private void next(@NonNull final Iterator<UriInterceptor> iterator,@NonNull final UriRequest request,@NonNull final UriCallback callback){
  if (iterator.hasNext()) {
    UriInterceptor t=iterator.next();
    if (Debugger.isEnableLog()) {
      Debugger.i("    %s: intercept, request = %s",t.getClass().getSimpleName(),request);
    }
    t.intercept(request,new UriCallback(){
      @Override public void onNext(){
        next(iterator,request,callback);
      }
      @Override public void onComplete(      int resultCode){
        callback.onComplete(resultCode);
      }
    }
);
  }
 else {
    callback.onNext();
  }
}
