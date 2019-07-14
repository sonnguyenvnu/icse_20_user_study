void increment(long time,boolean success){
  callCount++;
  errorCount+=success ? 0 : 1;
  totalTime+=time;
  times[index]=time;
  if (++index >= times.length)   index=0;
}
