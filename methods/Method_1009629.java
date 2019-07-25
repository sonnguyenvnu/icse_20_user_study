public void established(GENASubscription subscription){
  SwitchPowerControlPoint.LOGGER.info("Subscription with service established, listening for events, renewing in seconds: " + subscription.getActualDurationSeconds());
  presenter.onConnected();
}
