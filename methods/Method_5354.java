@Override public void prepare(Callback callback,long positionUs){
  this.callback=callback;
  playlistTracker.addListener(this);
  buildAndPrepareSampleStreamWrappers(positionUs);
}
