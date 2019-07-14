private void updateLiveEdgeTimeUs(RepresentationHolder representationHolder,long lastAvailableSegmentNum){
  liveEdgeTimeUs=manifest.dynamic ? representationHolder.getSegmentEndTimeUs(lastAvailableSegmentNum) : C.TIME_UNSET;
}
