public void reset(){
  unSubscribeFromObservable();
  compositeDisposable=null;
  context=null;
}
