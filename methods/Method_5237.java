private long getSegmentNum(RepresentationHolder representationHolder,@Nullable MediaChunk previousChunk,long loadPositionUs,long firstAvailableSegmentNum,long lastAvailableSegmentNum){
  return previousChunk != null ? previousChunk.getNextChunkIndex() : Util.constrainValue(representationHolder.getSegmentNum(loadPositionUs),firstAvailableSegmentNum,lastAvailableSegmentNum);
}
