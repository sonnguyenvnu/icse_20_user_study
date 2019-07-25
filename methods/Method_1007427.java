/** 
 * Configure.
 * @param r the r
 * @param url the url
 * @return the configuration
 * @throws HibernateException the hibernate exception
 */
public static Configuration configure(ReInitializable r,java.net.URL url) throws HibernateException {
  LOGGER.debug("Configuring....................." + url);
  r._configure(url);
  r.getOverrideConfig().config=url;
  r.getOverrideConfig().configuredBy=ConfiguredBy.URL;
  r.getOverrideConfig().properties.clear();
  return (Configuration)r;
}
