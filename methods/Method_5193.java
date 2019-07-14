@Override public void prepare(Callback callback,long positionUs){
  this.callback=callback;
  callback.onPrepared(this);
}
