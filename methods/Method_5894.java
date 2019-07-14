/** 
 * Returns the duration of the FLAC stream in microseconds. 
 */
public long durationUs(){
  return (totalSamples * 1000000L) / sampleRate;
}
