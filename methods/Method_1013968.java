/** 
 * Scan has 2 different behaviours. background/ foreground. Background scans can have much higher timeout. Foreground scans have only a short timeout as human users may become impatient. The underlying reason is that the jmDNS implementation  {@code MDNSClient#list(String)} has a default timeout of 6seconds when no ServiceInfo is found. When there are many participants, waiting 6 seconds for each non-existent type is too long.
 * @param isBackground true if it is background scan, false otherwise.
 */
private void scan(boolean isBackground){
  for (  MDNSDiscoveryParticipant participant : participants) {
    long start=System.currentTimeMillis();
    ServiceInfo[] services;
    if (isBackground) {
      services=mdnsClient.list(participant.getServiceType());
    }
 else {
      services=mdnsClient.list(participant.getServiceType(),FOREGROUND_SCAN_TIMEOUT);
    }
    logger.debug("{} services found for {}; duration: {}ms",services.length,participant.getServiceType(),System.currentTimeMillis() - start);
    for (    ServiceInfo service : services) {
      DiscoveryResult result=participant.createResult(service);
      if (result != null) {
        thingDiscovered(result);
      }
    }
  }
  for (  org.eclipse.smarthome.io.transport.mdns.discovery.MDNSDiscoveryParticipant participant : oldParticipants) {
    long start=System.currentTimeMillis();
    ServiceInfo[] services;
    if (isBackground) {
      services=mdnsClient.list(participant.getServiceType());
    }
 else {
      services=mdnsClient.list(participant.getServiceType(),FOREGROUND_SCAN_TIMEOUT);
    }
    logger.debug("{} services found for {}; duration: {}ms",services.length,participant.getServiceType(),System.currentTimeMillis() - start);
    for (    ServiceInfo service : services) {
      DiscoveryResult result=participant.createResult(service);
      if (result != null) {
        thingDiscovered(result);
      }
    }
  }
}
