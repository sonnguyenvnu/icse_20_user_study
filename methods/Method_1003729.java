boolean fire(){
  return FIRED_UPDATER.compareAndSet(this,0,1);
}
