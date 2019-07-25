synchronized public void run(){
  if (getControlPoint() == null) {
    throw new IllegalStateException("Callback must be executed through ControlPoint");
  }
  if (getService() instanceof LocalService) {
    establishLocalSubscription((LocalService)service);
  }
 else   if (getService() instanceof RemoteService) {
    establishRemoteSubscription((RemoteService)service);
  }
}
