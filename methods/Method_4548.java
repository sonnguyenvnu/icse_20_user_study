private long getFramePositionForTimeUs(long timeUs){
  long positionOffset=(timeUs * bitrate) / (C.MICROS_PER_SECOND * C.BITS_PER_BYTE);
  positionOffset=(positionOffset / frameSize) * frameSize;
  positionOffset=Util.constrainValue(positionOffset,0,dataSize - frameSize);
  return firstFrameBytePosition + positionOffset;
}
