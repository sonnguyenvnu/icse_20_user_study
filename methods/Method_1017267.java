@PreDestroy protected void destroy(){
  log.info("# << -- Destroy : " + this.name);
  try {
    mbeanServer.unregisterMBean(this.objectName);
  }
 catch (  Exception e) {
    throw new IllegalStateException("Error during unregistration of " + name + " into JMX:" + e,e);
  }
}
