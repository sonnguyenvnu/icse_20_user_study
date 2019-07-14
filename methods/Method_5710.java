/** 
 * Returns bitrate values for a set of tracks whose formats are given, using the given queue of already buffered  {@link MediaChunk} instances.
 * @param queue The queue of already buffered {@link MediaChunk} instances. Must not be modified.
 * @param formats The track formats.
 * @param maxDurationUs Maximum duration of chunks to be included in average bitrate values, inmicroseconds.
 * @param bitrates If not null, calculates bitrate values only for indexes set to Format.NO_VALUEand stores result in this array.
 * @return Bitrate values for the tracks. If for a track, a bitrate value can't be calculated,{@link Format#NO_VALUE} is set.
 * @see #getBitratesUsingFutureInfo(MediaChunkIterator[],Format[],long,int[])
 */
@VisibleForTesting static int[] getBitratesUsingPastInfo(List<? extends MediaChunk> queue,Format[] formats,long maxDurationUs,@Nullable int[] bitrates){
  if (bitrates == null) {
    bitrates=new int[formats.length];
    Arrays.fill(bitrates,Format.NO_VALUE);
  }
  if (maxDurationUs == 0) {
    return bitrates;
  }
  int queueAverageBitrate=getAverageQueueBitrate(queue,maxDurationUs);
  if (queueAverageBitrate == Format.NO_VALUE) {
    return bitrates;
  }
  int queueFormatBitrate=queue.get(queue.size() - 1).trackFormat.bitrate;
  if (queueFormatBitrate != Format.NO_VALUE) {
    float queueBitrateRatio=((float)queueAverageBitrate) / queueFormatBitrate;
    estimateBitrates(bitrates,formats,new int[]{queueFormatBitrate},new float[]{queueBitrateRatio});
  }
  return bitrates;
}
