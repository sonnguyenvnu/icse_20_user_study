@Override public void onDownstreamFormatChanged(EventTime eventTime,MediaLoadData mediaLoadData){
  logd(eventTime,"downstreamFormatChanged",Format.toLogString(mediaLoadData.trackFormat));
}
