/** 
 * Configure.
 * @param r the r
 * @param configFile the config file
 * @return the configuration
 * @throws HibernateException the hibernate exception
 */
public static Configuration configure(ReInitializable r,java.io.File configFile) throws HibernateException {
  System.err.println("Configuring....................." + configFile);
  r._configure(configFile);
  r.getOverrideConfig().properties.clear();
  return (Configuration)r;
}
