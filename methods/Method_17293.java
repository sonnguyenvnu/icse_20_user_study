/** 
 * Enables or disables the configuration management JMX bean. 
 */
void enableManagement(boolean enabled){
  requireNotClosed();
synchronized (configuration) {
    if (enabled) {
      JmxRegistration.registerMXBean(this,cacheMXBean,MBeanType.Configuration);
    }
 else {
      JmxRegistration.unregisterMXBean(this,MBeanType.Configuration);
    }
    configuration.setManagementEnabled(enabled);
  }
}
