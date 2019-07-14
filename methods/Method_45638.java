private boolean cancle0(boolean mayInterruptIfRunning){
  if (isDone()) {
    return false;
  }
synchronized (this) {
    if (isDone()) {
      return false;
    }
    this.cause=CANCELLATION_CAUSE;
    this.setDoneTime();
    if (hasWaiters()) {
      notifyAll();
    }
  }
  return true;
}
