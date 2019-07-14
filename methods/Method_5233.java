@Override public long getAdjustedSeekPositionUs(long positionUs,SeekParameters seekParameters){
  for (  RepresentationHolder representationHolder : representationHolders) {
    if (representationHolder.segmentIndex != null) {
      long segmentNum=representationHolder.getSegmentNum(positionUs);
      long firstSyncUs=representationHolder.getSegmentStartTimeUs(segmentNum);
      long secondSyncUs=firstSyncUs < positionUs && segmentNum < representationHolder.getSegmentCount() - 1 ? representationHolder.getSegmentStartTimeUs(segmentNum + 1) : firstSyncUs;
      return Util.resolveSeekPositionUs(positionUs,seekParameters,firstSyncUs,secondSyncUs);
    }
  }
  return positionUs;
}
