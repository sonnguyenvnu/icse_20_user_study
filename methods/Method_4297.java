/** 
 * Returns the specified duration scaled to take into account the speedup factor of this instance, in the same units as  {@code duration}.
 * @param duration The duration to scale taking into account speedup.
 * @return The specified duration scaled to take into account speedup, in the same units as{@code duration}.
 */
public long scaleDurationForSpeedup(long duration){
  if (outputBytes >= MIN_BYTES_FOR_SPEEDUP_CALCULATION) {
    return outputSampleRateHz == sampleRateHz ? Util.scaleLargeTimestamp(duration,inputBytes,outputBytes) : Util.scaleLargeTimestamp(duration,inputBytes * outputSampleRateHz,outputBytes * sampleRateHz);
  }
 else {
    return (long)((double)speed * duration);
  }
}
