public void ended(GENASubscription subscription,CancelReason reason,UpnpResponse responseStatus){
  SwitchPowerControlPoint.LOGGER.log(reason != null ? Level.WARNING : Level.INFO,"Subscription with service ended. " + (reason != null ? "Reason: " + reason : ""));
  presenter.onDisconnected();
}
