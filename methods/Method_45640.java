private boolean setFailure0(Throwable cause){
  if (isDone()) {
    return false;
  }
synchronized (this) {
    if (isDone()) {
      return false;
    }
    this.cause=cause;
    this.setDoneTime();
    if (hasWaiters()) {
      notifyAll();
    }
  }
  return true;
}
