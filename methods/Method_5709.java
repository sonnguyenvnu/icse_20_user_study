/** 
 * Returns bitrate values for a set of tracks whose upcoming media chunk iterators and formats are given. <p>If an average bitrate can't be calculated, an estimation is calculated using average bitrate of another track and the ratio of the bitrate values defined in the formats of the two tracks.
 * @param iterators An array of {@link MediaChunkIterator}s providing information about the sequence of upcoming media chunks for each track.
 * @param formats The track formats.
 * @param maxDurationUs Maximum duration of chunks to be included in average bitrate values, inmicroseconds.
 * @param bitrates If not null, stores bitrate values in this array.
 * @return Average bitrate values for the tracks. If for a track, an average bitrate or anestimation can't be calculated,  {@link Format#NO_VALUE} is set.
 * @see #getAverageBitrate(MediaChunkIterator,long)
 */
@VisibleForTesting static int[] getBitratesUsingFutureInfo(MediaChunkIterator[] iterators,Format[] formats,long maxDurationUs,@Nullable int[] bitrates){
  int trackCount=iterators.length;
  Assertions.checkArgument(trackCount == formats.length);
  if (trackCount == 0) {
    return new int[0];
  }
  if (bitrates == null) {
    bitrates=new int[trackCount];
  }
  if (maxDurationUs == 0) {
    Arrays.fill(bitrates,Format.NO_VALUE);
    return bitrates;
  }
  int[] formatBitrates=new int[trackCount];
  float[] bitrateRatios=new float[trackCount];
  boolean needEstimateBitrate=false;
  boolean canEstimateBitrate=false;
  for (int i=0; i < trackCount; i++) {
    int bitrate=getAverageBitrate(iterators[i],maxDurationUs);
    if (bitrate != Format.NO_VALUE) {
      int formatBitrate=formats[i].bitrate;
      formatBitrates[i]=formatBitrate;
      if (formatBitrate != Format.NO_VALUE) {
        bitrateRatios[i]=((float)bitrate) / formatBitrate;
        canEstimateBitrate=true;
      }
    }
 else {
      needEstimateBitrate=true;
      formatBitrates[i]=Format.NO_VALUE;
    }
    bitrates[i]=bitrate;
  }
  if (needEstimateBitrate && canEstimateBitrate) {
    estimateBitrates(bitrates,formats,formatBitrates,bitrateRatios);
  }
  return bitrates;
}
