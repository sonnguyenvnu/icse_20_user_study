/** 
 * @param version version
 * @return a IpVersion object.
 */
public static IpVersion register(IpVersion version){
  return registry.put(version.value(),version);
}
