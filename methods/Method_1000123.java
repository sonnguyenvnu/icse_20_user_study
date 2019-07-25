@Override public synchronized boolean has(byte[] key){
  return getUnchecked(key) != null;
}
