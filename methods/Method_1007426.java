/** 
 * Configure.
 * @param r the r
 * @param resource the resource
 * @return the configuration
 * @throws HibernateException the hibernate exception
 */
public static Configuration configure(ReInitializable r,String resource) throws HibernateException {
  LOGGER.debug("Configuring....................." + resource);
  r._configure(resource);
  r.getOverrideConfig().config=resource;
  r.getOverrideConfig().configuredBy=ConfiguredBy.STRING;
  r.getOverrideConfig().properties.clear();
  return (Configuration)r;
}
