/** 
 * Returns bitrate values for a set of tracks whose formats are given, using the given upcoming media chunk iterators and the queue of already buffered  {@link MediaChunk}s.
 * @param formats The track formats.
 * @param queue The queue of already buffered {@link MediaChunk}s. Must not be modified.
 * @param maxPastDurationUs Maximum duration of past chunks to be included in average bitratevalues, in microseconds.
 * @param iterators An array of {@link MediaChunkIterator}s providing information about the sequence of upcoming media chunks for each track.
 * @param maxFutureDurationUs Maximum duration of future chunks to be included in average bitratevalues, in microseconds.
 * @param useFormatBitrateAsLowerBound Whether to return the estimated bitrate only if it's higherthan the bitrate of the track's format.
 * @param bitrates An array into which the bitrate values will be written. If non-null, this arrayis the one that will be returned.
 * @return Bitrate values for the tracks. As long as the format of a track has set bitrate, abitrate value is set in the returned array. Otherwise it might be set to  {@link Format#NO_VALUE}.
 */
public static int[] getBitratesUsingPastAndFutureInfo(Format[] formats,List<? extends MediaChunk> queue,long maxPastDurationUs,MediaChunkIterator[] iterators,long maxFutureDurationUs,boolean useFormatBitrateAsLowerBound,@Nullable int[] bitrates){
  bitrates=getBitratesUsingFutureInfo(iterators,formats,maxFutureDurationUs,bitrates);
  getBitratesUsingPastInfo(queue,formats,maxPastDurationUs,bitrates);
  for (int i=0; i < bitrates.length; i++) {
    int bitrate=bitrates[i];
    if (bitrate == Format.NO_VALUE || (useFormatBitrateAsLowerBound && formats[i].bitrate != Format.NO_VALUE && bitrate < formats[i].bitrate)) {
      bitrates[i]=formats[i].bitrate;
    }
  }
  return bitrates;
}
