@Override public void onSurfaceSizeChanged(int width,int height){
  EventTime eventTime=generateReadingMediaPeriodEventTime();
  for (  AnalyticsListener listener : listeners) {
    listener.onSurfaceSizeChanged(eventTime,width,height);
  }
}
