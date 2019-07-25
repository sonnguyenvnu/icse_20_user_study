protected void ended(GENASubscription subscription,final CancelReason reason,UpnpResponse responseStatus){
  AVTransportControlPoint.LOGGER.log(reason != null ? Level.WARNING : Level.INFO,"Subscription with service ended. " + (reason != null ? "Reason: " + reason : ""));
  onDisconnect(reason);
}
