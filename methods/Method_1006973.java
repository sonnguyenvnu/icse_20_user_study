@Override public void put(ResultHolder holder) throws IllegalArgumentException {
  if (!isExpecting()) {
    throw new IllegalArgumentException("Not expecting a result.  Call expect() before put().");
  }
  results.add(holder);
  waits.release();
synchronized (lock) {
    lock.notifyAll();
  }
}
