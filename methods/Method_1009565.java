protected void execute() throws RouterException {
  if (getUpnpService().getRouter() == null) {
    log.fine("Router hasn't completed initialization, ignoring received search message");
    return;
  }
  if (!getInputMessage().isMANSSDPDiscover()) {
    log.fine("Invalid search request, no or invalid MAN ssdp:discover header: " + getInputMessage());
    return;
  }
  UpnpHeader searchTarget=getInputMessage().getSearchTarget();
  if (searchTarget == null) {
    log.fine("Invalid search request, did not contain ST header: " + getInputMessage());
    return;
  }
  List<NetworkAddress> activeStreamServers=getUpnpService().getRouter().getActiveStreamServers(getInputMessage().getLocalAddress());
  if (activeStreamServers.size() == 0) {
    log.fine("Aborting search response, no active stream servers found (network disabled?)");
    return;
  }
  for (  NetworkAddress activeStreamServer : activeStreamServers) {
    sendResponses(searchTarget,activeStreamServer);
  }
}
