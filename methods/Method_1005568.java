private void next(@NonNull final Iterator<UriHandler> iterator,@NonNull final UriRequest request,@NonNull final UriCallback callback){
  if (iterator.hasNext()) {
    UriHandler t=iterator.next();
    t.handle(request,new UriCallback(){
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
