protected void deactivate(){
  lastKnownInterfaceAddresses=Collections.emptyList();
  networkAddressChangeListeners=ConcurrentHashMap.newKeySet();
  if (networkInterfacePollFuture != null) {
    networkInterfacePollFuture.cancel(true);
    networkInterfacePollFuture=null;
  }
}
