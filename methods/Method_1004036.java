/** 
 * @see GridRegistry#add(RemoteProxy)
 */
public void add(RemoteProxy proxy){
  if (proxy == null) {
    return;
  }
  LOG.debug("Received a node registration request {}",proxy);
  try {
    lock.lock();
    if (proxy instanceof DockerSeleniumRemoteProxy) {
      if (proxies.contains(proxy)) {
        LOG.debug("Proxy '{}' is already registered.",proxy);
        return;
      }
    }
 else {
      removeIfPresent(proxy);
    }
    if (registeringProxies.contains(proxy)) {
      LOG.debug("Proxy '{}' is already queued for registration.",proxy);
      return;
    }
    registeringProxies.add(proxy);
    fireMatcherStateChanged();
  }
  finally {
    lock.unlock();
  }
  boolean listenerOk=true;
  try {
    if (proxy instanceof RegistrationListener) {
      ((RegistrationListener)proxy).beforeRegistration();
    }
  }
 catch (  Throwable t) {
    LOG.error("Error running the registration listener on {}, {}",proxy,t.getMessage());
    t.printStackTrace();
    listenerOk=false;
  }
  try {
    lock.lock();
    registeringProxies.remove(proxy);
    if (listenerOk) {
      if (proxy instanceof SelfHealingProxy) {
        ((SelfHealingProxy)proxy).startPolling();
      }
      proxies.add(proxy);
      LOG.info("Registered a node {}",proxy);
      fireMatcherStateChanged();
    }
  }
  finally {
    lock.unlock();
  }
}
