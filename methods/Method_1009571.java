void shutdown(){
  log.fine("Cancelling all outgoing subscriptions to remote devices during shutdown");
  List<RemoteGENASubscription> remoteSubscriptions=new ArrayList<>();
  for (  RegistryItem<String,RemoteGENASubscription> item : getSubscriptionItems()) {
    remoteSubscriptions.add(item.getItem());
  }
  for (  RemoteGENASubscription remoteSubscription : remoteSubscriptions) {
    registry.getProtocolFactory().createSendingUnsubscribe(remoteSubscription).run();
  }
  log.fine("Removing all remote devices from registry during shutdown");
  removeAll(true);
}
