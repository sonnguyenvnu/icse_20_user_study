@Override public long getTimeUs(long position){
  return timesUs[Util.binarySearchFloor(positions,position,true,true)];
}
