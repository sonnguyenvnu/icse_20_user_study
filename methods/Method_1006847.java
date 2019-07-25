/** 
 * Unregister all the  {@link Job} instances that were registered by thispost processor.
 * @see org.springframework.beans.factory.DisposableBean#destroy()
 */
@Override public void destroy() throws Exception {
  for (  String name : jobNames) {
    if (logger.isDebugEnabled()) {
      logger.debug("Unregistering job: " + name);
    }
    jobRegistry.unregister(name);
  }
  jobNames.clear();
}
