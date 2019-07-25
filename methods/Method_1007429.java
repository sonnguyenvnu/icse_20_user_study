/** 
 * Configure.
 * @param r the r
 * @return the configuration
 * @throws HibernateException the hibernate exception
 */
public static Configuration configure(ReInitializable r) throws HibernateException {
  LOGGER.debug("Configuring..................... EMPTY..");
  r._configure();
  r.getOverrideConfig().config=null;
  r.getOverrideConfig().configuredBy=ConfiguredBy.NONE;
  r.getOverrideConfig().properties.clear();
  return (Configuration)r;
}
