/** 
 * Returns the sample index for the sample at given position.
 * @param timeUs Time position in microseconds in the FLAC stream.
 * @return The sample index for the sample at given position.
 */
public long getSampleIndex(long timeUs){
  long sampleIndex=(timeUs * sampleRate) / C.MICROS_PER_SECOND;
  return Util.constrainValue(sampleIndex,0,totalSamples - 1);
}
