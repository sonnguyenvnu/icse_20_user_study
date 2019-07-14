@Override public void onPlayerStateChanged(EventTime eventTime,boolean playWhenReady,int state){
  logd(eventTime,"state",playWhenReady + ", " + getStateString(state));
}
