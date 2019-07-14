@Override public synchronized TwitterStream onStatus(final Consumer<Status> action){
  streamListeners.add(new StatusAdapter(){
    @Override public void onStatus(    Status status){
      action.accept(status);
    }
  }
);
  updateListeners();
  return this;
}
