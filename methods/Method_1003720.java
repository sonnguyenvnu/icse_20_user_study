/** 
 * Set the state of the JMX publisher.
 * @param enabled True if metrics are published to JMX. False otherwise
 * @return this
 */
public JmxConfig enable(boolean enabled){
  this.enabled=enabled;
  return this;
}
