public void replace(DLNAResource a,DLNAResource b){
  ID item=getItem(parseIndex(a.getId()));
  if (item != null) {
synchronized (lock) {
      lock.writeLock().lock();
      try {
        item.setRef(b);
      }
  finally {
        lock.writeLock().unlock();
      }
    }
  }
}
