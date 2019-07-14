@Override public int[] getBitrates(Format[] formats,List<? extends MediaChunk> queue,MediaChunkIterator[] iterators,@Nullable int[] bitrates){
  if (maxFutureDurationUs > 0 || maxPastDurationUs > 0) {
    return TrackSelectionUtil.getBitratesUsingPastAndFutureInfo(formats,queue,maxPastDurationUs,iterators,maxFutureDurationUs,useFormatBitrateAsLowerBound,bitrates);
  }
  return TrackSelectionUtil.getFormatBitrates(formats,bitrates);
}
