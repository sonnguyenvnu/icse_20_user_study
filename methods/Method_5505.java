private void processManifest(){
  for (int i=0; i < mediaPeriods.size(); i++) {
    mediaPeriods.get(i).updateManifest(manifest);
  }
  long startTimeUs=Long.MAX_VALUE;
  long endTimeUs=Long.MIN_VALUE;
  for (  StreamElement element : manifest.streamElements) {
    if (element.chunkCount > 0) {
      startTimeUs=Math.min(startTimeUs,element.getStartTimeUs(0));
      endTimeUs=Math.max(endTimeUs,element.getStartTimeUs(element.chunkCount - 1) + element.getChunkDurationUs(element.chunkCount - 1));
    }
  }
  Timeline timeline;
  if (startTimeUs == Long.MAX_VALUE) {
    long periodDurationUs=manifest.isLive ? C.TIME_UNSET : 0;
    timeline=new SinglePeriodTimeline(periodDurationUs,0,0,0,true,manifest.isLive,tag);
  }
 else   if (manifest.isLive) {
    if (manifest.dvrWindowLengthUs != C.TIME_UNSET && manifest.dvrWindowLengthUs > 0) {
      startTimeUs=Math.max(startTimeUs,endTimeUs - manifest.dvrWindowLengthUs);
    }
    long durationUs=endTimeUs - startTimeUs;
    long defaultStartPositionUs=durationUs - C.msToUs(livePresentationDelayMs);
    if (defaultStartPositionUs < MIN_LIVE_DEFAULT_START_POSITION_US) {
      defaultStartPositionUs=Math.min(MIN_LIVE_DEFAULT_START_POSITION_US,durationUs / 2);
    }
    timeline=new SinglePeriodTimeline(C.TIME_UNSET,durationUs,startTimeUs,defaultStartPositionUs,true,true,tag);
  }
 else {
    long durationUs=manifest.durationUs != C.TIME_UNSET ? manifest.durationUs : endTimeUs - startTimeUs;
    timeline=new SinglePeriodTimeline(startTimeUs + durationUs,durationUs,startTimeUs,0,true,false,tag);
  }
  refreshSourceInfo(timeline,manifest);
}
