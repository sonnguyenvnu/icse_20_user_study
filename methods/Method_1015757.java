public synchronized TimeService stop(){
  if (task != null) {
    task.cancel(false);
    task=null;
  }
  return this;
}
