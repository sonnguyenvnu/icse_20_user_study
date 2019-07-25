@Override public void stop(){
synchronized (this) {
    this.setDone();
    this.theStateQueue.finishAll();
    this.notifyAll();
  }
}
