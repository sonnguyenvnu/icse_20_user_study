@Override public boolean isDone(){
  return _thread != null && !_thread.isAlive();
}
