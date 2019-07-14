/** 
 * Checks if proxy is created and throws an exception if not.
 */
protected void assertProxyIsCreated(){
  if (destClassWriter == null) {
    throw new ProxettaException("Target not accepted yet!");
  }
}
