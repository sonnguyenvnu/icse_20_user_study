@Override public RangedUri getSegmentUrl(long segmentNum){
  return new RangedUri(null,chunkIndex.offsets[(int)segmentNum],chunkIndex.sizes[(int)segmentNum]);
}
