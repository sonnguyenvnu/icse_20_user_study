/** 
 * Loads system properties with given prefix. If prefix is <code>null</code> it will not be ignored.
 */
public Props loadSystemProperties(final String prefix){
  final Properties environmentProperties=System.getProperties();
  load(environmentProperties,prefix);
  return this;
}
