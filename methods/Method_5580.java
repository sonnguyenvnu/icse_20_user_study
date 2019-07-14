@Override public long getEventTime(int index){
  Assertions.checkArgument(index >= 0);
  Assertions.checkArgument(index < cueTimesUs.length);
  return cueTimesUs[index];
}
