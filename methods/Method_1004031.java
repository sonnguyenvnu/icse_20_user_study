/** 
 * If possible, starts a new proxy that can satisfy the requested capabilities. If too many proxies are already running, then a new proxy will not be started. If the request has been made previously, then a new proxy will not be started. If a proxy that would otherwise be able to service the request is currently cleaning up and will be available shortly, then a new proxy will not be started.
 * @param desiredCapabilities capabilities of the proxy to be started.
 */
public synchronized void start(Map<String,Object> desiredCapabilities){
  if (startedContainers.size() >= this.maxContainers) {
    LOGGER.debug("Not starting new container, there are [{}] of max [{}] created.",startedContainers.size(),this.maxContainers);
    return;
  }
  if (nodesAvailable(desiredCapabilities)) {
    LOGGER.debug("A node is coming up soon for {}, won't start a new node yet.",desiredCapabilities);
    return;
  }
  if (filter.hasRequestBeenProcessed(desiredCapabilities)) {
    LOGGER.debug("Request {}, has been processed and it is waiting for a node.",desiredCapabilities);
    return;
  }
  LOGGER.debug("No proxy available for new session, starting new.");
  this.startContainer(desiredCapabilities);
  filter.cleanProcessedCapabilities();
}
