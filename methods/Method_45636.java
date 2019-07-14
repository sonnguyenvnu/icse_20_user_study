private void incWaiters(){
  if (waiters == Short.MAX_VALUE) {
    throw new IllegalStateException("too many waiters: " + this);
  }
  waiters++;
}
