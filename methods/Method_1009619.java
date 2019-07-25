@Override public void init(Service service){
  this.service=service;
  this.callback=new MonitorSubscriptionCallback(service);
  view.setPresenter(this);
  view.setTitle("Monitoring Service: " + service.getServiceType().toFriendlyString());
}
