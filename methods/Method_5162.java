private void refreshClippedTimeline(Timeline timeline){
  long windowStartUs;
  long windowEndUs;
  timeline.getWindow(0,window);
  long windowPositionInPeriodUs=window.getPositionInFirstPeriodUs();
  if (clippingTimeline == null || mediaPeriods.isEmpty() || allowDynamicClippingUpdates) {
    windowStartUs=startUs;
    windowEndUs=endUs;
    if (relativeToDefaultPosition) {
      long windowDefaultPositionUs=window.getDefaultPositionUs();
      windowStartUs+=windowDefaultPositionUs;
      windowEndUs+=windowDefaultPositionUs;
    }
    periodStartUs=windowPositionInPeriodUs + windowStartUs;
    periodEndUs=endUs == C.TIME_END_OF_SOURCE ? C.TIME_END_OF_SOURCE : windowPositionInPeriodUs + windowEndUs;
    int count=mediaPeriods.size();
    for (int i=0; i < count; i++) {
      mediaPeriods.get(i).updateClipping(periodStartUs,periodEndUs);
    }
  }
 else {
    windowStartUs=periodStartUs - windowPositionInPeriodUs;
    windowEndUs=endUs == C.TIME_END_OF_SOURCE ? C.TIME_END_OF_SOURCE : periodEndUs - windowPositionInPeriodUs;
  }
  try {
    clippingTimeline=new ClippingTimeline(timeline,windowStartUs,windowEndUs);
  }
 catch (  IllegalClippingException e) {
    clippingError=e;
    return;
  }
  refreshSourceInfo(clippingTimeline,manifest);
}
