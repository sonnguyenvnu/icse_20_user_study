@Override public synchronized TwitterStream onException(final Consumer<Exception> action){
  streamListeners.add(new StatusAdapter(){
    @Override public void onException(    Exception ex){
      action.accept(ex);
    }
  }
);
  updateListeners();
  return this;
}
