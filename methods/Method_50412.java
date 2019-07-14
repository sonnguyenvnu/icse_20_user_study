/** 
 * Build zookeeper path prefix string.
 * @param applicationName the application name
 * @return the string
 */
public static String buildZookeeperPathPrefix(final String applicationName){
  return String.join("-",CommonConstant.PATH_SUFFIX,applicationName);
}
