public void init(Service service){
  this.service=service;
  eventCallback=new RenderingControlCallback(service){
    @Override protected void onDisconnect(    final CancelReason reason){
      SwingUtilities.invokeLater(new Runnable(){
        public void run(){
          String title="DISCONNECTED: " + (reason != null ? reason.toString() : "");
          view.setTitle(title);
          for (int i=0; i < RenderingControlView.SUPPORTED_INSTANCES; i++) {
            view.getInstanceView(i).setSelectionEnabled(false);
          }
        }
      }
);
    }
    @Override protected void onMasterVolumeChanged(    final int instanceId,    final int newVolume){
      SwingUtilities.invokeLater(new Runnable(){
        public void run(){
          view.getInstanceView(instanceId).setVolume(newVolume);
        }
      }
);
    }
  }
;
  StateVariableAllowedValueRange volumeRange=null;
  if (service.getStateVariable("Volume") != null) {
    volumeRange=service.getStateVariable("Volume").getTypeDetails().getAllowedValueRange();
  }
  view.init(volumeRange);
  view.setPresenter(this);
  view.setTitle(service.getDevice().getDetails().getFriendlyName());
  controlPoint.execute(eventCallback);
  RenderingControlPoint.LOGGER.info("Querying initial state of RenderingControl service");
  for (int i=0; i < RenderingControlView.SUPPORTED_INSTANCES; i++) {
    updateVolume(i);
  }
}
