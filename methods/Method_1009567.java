void maintain(){
  if (getDeviceItems().isEmpty())   return;
  Set<RegistryItem<UDN,LocalDevice>> expiredLocalItems=new HashSet<>();
  int aliveIntervalMillis=registry.getConfiguration().getAliveIntervalMillis();
  if (aliveIntervalMillis > 0) {
    long now=System.currentTimeMillis();
    if (now - lastAliveIntervalTimestamp > aliveIntervalMillis) {
      lastAliveIntervalTimestamp=now;
      for (      RegistryItem<UDN,LocalDevice> localItem : getDeviceItems()) {
        if (isAdvertised(localItem.getKey())) {
          log.finer("Flooding advertisement of local item: " + localItem);
          expiredLocalItems.add(localItem);
        }
      }
    }
  }
 else {
    lastAliveIntervalTimestamp=0;
    for (    RegistryItem<UDN,LocalDevice> localItem : getDeviceItems()) {
      if (isAdvertised(localItem.getKey()) && localItem.getExpirationDetails().hasExpired(true)) {
        log.finer("Local item has expired: " + localItem);
        expiredLocalItems.add(localItem);
      }
    }
  }
  for (  RegistryItem<UDN,LocalDevice> expiredLocalItem : expiredLocalItems) {
    log.fine("Refreshing local device advertisement: " + expiredLocalItem.getItem());
    advertiseAlive(expiredLocalItem.getItem());
    expiredLocalItem.getExpirationDetails().stampLastRefresh();
  }
  Set<RegistryItem<String,LocalGENASubscription>> expiredIncomingSubscriptions=new HashSet<>();
  for (  RegistryItem<String,LocalGENASubscription> item : getSubscriptionItems()) {
    if (item.getExpirationDetails().hasExpired(false)) {
      expiredIncomingSubscriptions.add(item);
    }
  }
  for (  RegistryItem<String,LocalGENASubscription> subscription : expiredIncomingSubscriptions) {
    log.fine("Removing expired: " + subscription);
    removeSubscription(subscription.getItem());
    subscription.getItem().end(CancelReason.EXPIRED);
  }
}
