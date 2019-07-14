private long durationUsToFrames(long durationUs){
  return (durationUs * outputSampleRate) / C.MICROS_PER_SECOND;
}
