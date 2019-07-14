private long framesToDurationUs(long frameCount){
  return (frameCount * C.MICROS_PER_SECOND) / outputSampleRate;
}
