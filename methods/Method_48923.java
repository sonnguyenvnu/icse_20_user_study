@Override public void stopTimer(){
  Preconditions.checkArgument(runningTimer,"No timer running");
  measuredTimeNs+=(System.nanoTime() - startTimeNs);
  runningTimer=false;
}
