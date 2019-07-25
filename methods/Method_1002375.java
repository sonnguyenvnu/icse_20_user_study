public void reset(final Callback<None> callback){
  markDown(new Callback<None>(){
    @Override public void onSuccess(    None none){
      markUp(callback);
    }
    @Override public void onError(    Throwable e){
      callback.onError(e);
    }
  }
);
}
