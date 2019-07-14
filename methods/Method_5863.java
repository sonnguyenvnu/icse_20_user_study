@Override public void onMetadata(EventTime eventTime,Metadata metadata){
  logd("metadata [" + getEventTimeString(eventTime) + ", ");
  printMetadata(metadata,"  ");
  logd("]");
}
