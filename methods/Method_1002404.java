/** 
 * starts the stores and request the latest data from zk
 */
@Override public void start(Callback<None> callback){
  Callback<None> warmUpCallback=new Callback<None>(){
    @Override public void onError(    Throwable e){
      callback.onError(e);
    }
    @Override public void onSuccess(    None result){
      warmUp(callback);
    }
  }
;
  _zkAwareStore.start(Callbacks.empty());
  _fsStore.start(warmUpCallback);
}
