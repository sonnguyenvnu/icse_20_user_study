protected boolean setSuccess0(V result){
  if (isDone()) {
    return false;
  }
synchronized (this) {
    if (isDone()) {
      return false;
    }
    if (this.result == null) {
      this.result=result;
    }
    this.setDoneTime();
    if (hasWaiters()) {
      notifyAll();
    }
  }
  return true;
}
