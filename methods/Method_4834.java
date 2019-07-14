@Override public SeekPoints getSeekPoints(long timeUs){
  long positionOffset=(timeUs * averageBytesPerSecond) / C.MICROS_PER_SECOND;
  positionOffset=(positionOffset / blockAlignment) * blockAlignment;
  positionOffset=Util.constrainValue(positionOffset,0,dataSize - blockAlignment);
  long seekPosition=dataStartPosition + positionOffset;
  long seekTimeUs=getTimeUs(seekPosition);
  SeekPoint seekPoint=new SeekPoint(seekTimeUs,seekPosition);
  if (seekTimeUs >= timeUs || positionOffset == dataSize - blockAlignment) {
    return new SeekPoints(seekPoint);
  }
 else {
    long secondSeekPosition=seekPosition + blockAlignment;
    long secondSeekTimeUs=getTimeUs(secondSeekPosition);
    SeekPoint secondSeekPoint=new SeekPoint(secondSeekTimeUs,secondSeekPosition);
    return new SeekPoints(seekPoint,secondSeekPoint);
  }
}
