@Override public void onDroppedVideoFrames(EventTime eventTime,int count,long elapsedMs){
  logd(eventTime,"droppedFrames",Integer.toString(count));
}
