/** 
 * Returns the number of input frames corresponding to  {@code durationUs} microseconds of audio.
 */
private int durationUsToFrames(long durationUs){
  return (int)((durationUs * sampleRateHz) / C.MICROS_PER_SECOND);
}
