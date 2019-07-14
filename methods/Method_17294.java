/** 
 * Enables or disables the statistics JMX bean. 
 */
void enableStatistics(boolean enabled){
  requireNotClosed();
synchronized (configuration) {
    if (enabled) {
      JmxRegistration.registerMXBean(this,statistics,MBeanType.Statistics);
    }
 else {
      JmxRegistration.unregisterMXBean(this,MBeanType.Statistics);
    }
    statistics.enable(enabled);
    configuration.setStatisticsEnabled(enabled);
  }
}
