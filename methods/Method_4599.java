@Override public SeekPoints getSeekPoints(long timeUs){
  timeUs=Util.constrainValue(timeUs,0,durationUs);
  Pair<Long,Long> timeMsAndPosition=linearlyInterpolate(C.usToMs(timeUs),referenceTimesMs,referencePositions);
  timeUs=C.msToUs(timeMsAndPosition.first);
  long position=timeMsAndPosition.second;
  return new SeekPoints(new SeekPoint(timeUs,position));
}
