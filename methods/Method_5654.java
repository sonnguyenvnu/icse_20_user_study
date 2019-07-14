@Override public long getEventTime(int index){
  Assertions.checkArgument(index >= 0);
  Assertions.checkArgument(index < sortedCueTimesUs.length);
  return sortedCueTimesUs[index];
}
