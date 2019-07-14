@Override public boolean isRunning(){
  return !executor.isTerminated() && !executor.isTerminating();
}
