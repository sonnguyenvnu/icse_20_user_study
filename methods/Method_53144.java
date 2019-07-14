@Override public synchronized long getAverageTime(){
  int stopIndex=Math.min(Math.abs((int)callCount),times.length);
  if (stopIndex == 0) {
    return 0;
  }
  long totalTime=0;
  for (int i=0; i < stopIndex; i++) {
    totalTime+=times[i];
  }
  return totalTime / stopIndex;
}
