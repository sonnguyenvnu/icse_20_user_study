/** 
 * Creates properties from classpath.
 */
public static Properties createFromClasspath(final String... rootTemplate){
  Properties p=new Properties();
  return loadFromClasspath(p,rootTemplate);
}
