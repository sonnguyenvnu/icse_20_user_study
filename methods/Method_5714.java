private static int getAverageQueueBitrate(List<? extends MediaChunk> queue,long maxDurationUs){
  if (queue.isEmpty()) {
    return Format.NO_VALUE;
  }
  MediaChunkListIterator iterator=new MediaChunkListIterator(getSingleFormatSubQueue(queue),true);
  return getAverageBitrate(iterator,maxDurationUs);
}
