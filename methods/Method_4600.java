@Override public long getTimeUs(long position){
  Pair<Long,Long> positionAndTimeMs=linearlyInterpolate(position,referencePositions,referenceTimesMs);
  return C.msToUs(positionAndTimeMs.second);
}
