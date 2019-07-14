/** 
 * ?????Key
 * @param config   ??
 * @param protocol ??
 * @return ???
 */
public static String buildMeshKey(AbstractInterfaceConfig config,String protocol){
  return ConfigUniqueNameGenerator.getUniqueName(config);
}
