@Override public void put(final T item){
  LOG.debug("{}: putting back an item {}",_name,item);
  boolean disposed=false;
  boolean returned=false;
synchronized (_lock) {
    if (_item.get() == null || _item.get() != item) {
      LOG.debug("{}: given item {} does not reference match current item {}",new Object[]{_name,item,_item.get()});
      disposed=doDispose(item);
    }
 else {
      if (_lifecycle.validatePut(item)) {
        LOG.debug("{}: returning an item {} that passed validation",_name,item);
        returned=doReturn(item);
      }
 else {
        LOG.debug("{}: disposing an item {} that failed validation",_name,item);
        disposed=doDispose(item);
      }
    }
  }
  if (disposed) {
    doDestroy(item,BAD,() -> doAttemptShutdown());
  }
  if (returned) {
    doAttemptShutdown();
  }
}
