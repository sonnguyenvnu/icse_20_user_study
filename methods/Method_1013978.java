@Override public synchronized void release(){
  this.configDescriptionProvider.removeAll(bundle);
}
