@Override public void onUpstreamDiscarded(EventTime eventTime,MediaLoadData mediaLoadData){
  logd(eventTime,"upstreamDiscarded",Format.toLogString(mediaLoadData.trackFormat));
}
