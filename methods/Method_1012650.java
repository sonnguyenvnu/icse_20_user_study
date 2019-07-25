public void start(DLNAResource dlna){
  if (getPlayingRes() != dlna) {
    stop();
  }
  setPlayingRes(dlna);
  if (startStop == null) {
    startStop=new StartStopListenerDelegate(ip);
  }
  startStop.setRenderer(this);
  startStop.start(getPlayingRes());
}
