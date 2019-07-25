@Override public void run(){
  if (!running.compareAndSet(false,true)) {
    throw new IllegalStateException("Thread is already running");
  }
}
