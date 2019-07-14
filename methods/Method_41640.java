/** 
 * Returns the cluster wide value for this scheduler instance's id, based on a system property
 * @return the value of the system property named by the value of {@link #getSystemPropertyName()} - which defaultsto  {@link #SYSTEM_PROPERTY}.
 * @throws SchedulerException Shouldn't a value be found
 */
public String generateInstanceId() throws SchedulerException {
  String property=System.getProperty(getSystemPropertyName());
  if (property == null) {
    throw new SchedulerException("No value for '" + SYSTEM_PROPERTY + "' system property found, please configure your environment accordingly!");
  }
  if (getPrepend() != null)   property=getPrepend() + property;
  if (getPostpend() != null)   property=property + getPostpend();
  return property;
}
