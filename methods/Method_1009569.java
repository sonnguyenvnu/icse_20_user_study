void maintain(){
  if (getDeviceItems().isEmpty())   return;
  Map<UDN,RemoteDevice> expiredRemoteDevices=new HashMap<>();
  for (  RegistryItem<UDN,RemoteDevice> remoteItem : getDeviceItems()) {
    if (log.isLoggable(Level.FINEST))     log.finest("Device '" + remoteItem.getItem() + "' expires in seconds: " + remoteItem.getExpirationDetails().getSecondsUntilExpiration());
    if (remoteItem.getExpirationDetails().hasExpired(false)) {
      expiredRemoteDevices.put(remoteItem.getKey(),remoteItem.getItem());
    }
  }
  for (  RemoteDevice remoteDevice : expiredRemoteDevices.values()) {
    if (log.isLoggable(Level.FINE))     log.fine("Removing expired: " + remoteDevice);
    remove(remoteDevice);
  }
  Set<RemoteGENASubscription> expiredOutgoingSubscriptions=new HashSet<>();
  for (  RegistryItem<String,RemoteGENASubscription> item : getSubscriptionItems()) {
    if (item.getExpirationDetails().hasExpired(true)) {
      expiredOutgoingSubscriptions.add(item.getItem());
    }
  }
  for (  RemoteGENASubscription subscription : expiredOutgoingSubscriptions) {
    if (log.isLoggable(Level.FINEST))     log.fine("Renewing outgoing subscription: " + subscription);
    renewOutgoingSubscription(subscription);
  }
}
