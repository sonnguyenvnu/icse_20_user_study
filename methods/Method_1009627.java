@Override protected void connect(Service service){
  if (service == null)   return;
  callback=new SwitchPowerSubscriptionCallback(service,this);
  upnpService.getControlPoint().execute(callback);
}
