@Override public synchronized long getCacheSpace(){
  Assertions.checkState(!released);
  return totalSpace;
}
