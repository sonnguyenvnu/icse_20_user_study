public synchronized boolean allowed(InetAddress addr){
  boolean log=isFirstDecision(addr);
  if (matchers.isEmpty()) {
    if (log) {
      LOGGER.trace("IP Filter: No IP filter specified, access granted to {}",addr.getHostAddress());
    }
    return true;
  }
  for (  Predicate p : matchers) {
    if (p.match(addr)) {
      if (log) {
        LOGGER.trace("IP Filter: Access granted to {} with rule: {}",addr.getHostAddress(),p);
      }
      return true;
    }
  }
  if (log) {
    LOGGER.info("IP Filter: Access denied to {}",addr.getHostName());
  }
  return false;
}
