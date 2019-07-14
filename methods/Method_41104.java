/** 
 * Get the name under which the QuartzScheduler should be registered with  the local MBeanServer.  If unset, defaults to the value calculated by  <code>generateJMXObjectName<code>.
 * @see #generateJMXObjectName(String,String)
 */
public String getJMXObjectName(){
  return (jmxObjectName == null) ? generateJMXObjectName(name,instanceId) : jmxObjectName;
}
