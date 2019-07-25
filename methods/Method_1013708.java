@Override public void update(Object args,Observable observable){
  logger.info("[update]{}",args);
  this.currentState.refresh();
  notifyObservers(this);
}
