@Override public void onDecoderInitialized(EventTime eventTime,int trackType,String decoderName,long initializationDurationMs){
  logd(eventTime,"decoderInitialized",getTrackTypeString(trackType) + ", " + decoderName);
}
