@Override public synchronized void release(){
  this.bindingInfoProvider.removeAll(bundle);
  this.configDescriptionProvider.removeAll(bundle);
}
