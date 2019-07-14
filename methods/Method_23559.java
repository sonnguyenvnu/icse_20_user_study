public boolean isStopped(){
  return thread == null || !thread.isAlive();
}
