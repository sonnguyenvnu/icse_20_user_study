@Override public void signal(){
  if (waiters > 0) {
synchronized (obj) {
      if (waiters > 0) {
        obj.notify();
      }
    }
  }
}
