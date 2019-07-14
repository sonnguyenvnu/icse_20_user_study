@Override public long getDurationUs(){
  long numFrames=dataSize / blockAlignment;
  return (numFrames * C.MICROS_PER_SECOND) / sampleRateHz;
}
