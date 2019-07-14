/** 
 * Returns average bitrate for chunks in bits per second. Chunks are included in average until {@code maxDurationMs} or the first unknown length chunk.
 * @param iterator Iterator for media chunk sequences.
 * @param maxDurationUs Maximum duration of chunks to be included in average bitrate, inmicroseconds.
 * @return Average bitrate for chunks in bits per second, or {@link Format#NO_VALUE} if there areno chunks or the first chunk length is unknown.
 */
public static int getAverageBitrate(MediaChunkIterator iterator,long maxDurationUs){
  long totalDurationUs=0;
  long totalLength=0;
  while (iterator.next()) {
    long chunkLength=iterator.getDataSpec().length;
    if (chunkLength == C.LENGTH_UNSET) {
      break;
    }
    long chunkDurationUs=iterator.getChunkEndTimeUs() - iterator.getChunkStartTimeUs();
    if (totalDurationUs + chunkDurationUs >= maxDurationUs) {
      totalLength+=chunkLength * (maxDurationUs - totalDurationUs) / chunkDurationUs;
      totalDurationUs=maxDurationUs;
      break;
    }
    totalDurationUs+=chunkDurationUs;
    totalLength+=chunkLength;
  }
  return totalDurationUs == 0 ? Format.NO_VALUE : (int)(totalLength * C.BITS_PER_BYTE * C.MICROS_PER_SECOND / totalDurationUs);
}
