public void init(Service service){
  this.service=service;
  this.eventCallback=new AVTransportCallback(service){
    @Override protected void onDisconnect(    final CancelReason reason){
      SwingUtilities.invokeLater(new Runnable(){
        public void run(){
          String title="DISCONNECTED: " + (reason != null ? reason.toString() : "");
          view.setTitle(title);
          view.setSelectionEnabled(false);
        }
      }
);
    }
    @Override protected void onStateChange(    int instanceId,    TransportState state){
      view.getInstanceView(instanceId).setState(state);
    }
    @Override protected void onPlayModeChange(    int instanceId,    PlayMode playMode){
      view.getInstanceView(instanceId).setPlayMode(playMode);
    }
    @Override protected void onCurrentTrackURIChange(    int instanceId,    String uri){
      view.getInstanceView(instanceId).setCurrentTrackURI(uri);
    }
  }
;
  view.setPresenter(this);
  view.setTitle(service.getDevice().getDetails().getFriendlyName());
  controlPoint.execute(eventCallback);
  AVTransportControlPoint.LOGGER.info("Querying initial state of AVTransport service");
  for (int i=0; i < AVTransportView.SUPPORTED_INSTANCES; i++) {
    updateTransportInfo(i);
    updateMediaInfo(i);
    onUpdatePositionInfo(i);
  }
}
