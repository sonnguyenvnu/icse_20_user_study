@Override public void onDecoderInputFormatChanged(EventTime eventTime,int trackType,Format format){
  logd(eventTime,"decoderInputFormatChanged",getTrackTypeString(trackType) + ", " + Format.toLogString(format));
}
