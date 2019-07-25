private void start(){
  for (  InetAddress address : getAllInetAddresses()) {
    createJmDNSByAddress(address);
  }
  for (  ServiceDescription description : activeServices) {
    try {
      registerServiceInternal(description);
    }
 catch (    IOException e) {
      logger.warn("Exception while registering service {}",description,e);
    }
  }
}
