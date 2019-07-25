public void init(Service service){
  serviceReference=service.getReference();
  wakeOnLANBytes=service.getDevice() instanceof RemoteDevice ? ((RemoteDevice)service.getDevice()).getIdentity().getWakeOnLANBytes() : null;
  reconnectView.setPresenter(this);
  init(reconnectView);
}
