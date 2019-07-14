private long inputFramesToDurationUs(long frameCount){
  return (frameCount * C.MICROS_PER_SECOND) / inputSampleRate;
}
