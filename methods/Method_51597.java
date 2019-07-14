/** 
 * Return a map of all the short names of classes we maintain mappings for. The names are keyed by the classes themselves.
 * @return Map<Class, String>
 */
private static Map<Class<?>,String> computeClassShortNames(){
  Map<Class<?>,String> map=new HashMap<>();
  map.putAll(PRIMITIVE_TYPE_NAMES.asInverseWithShortName());
  map.putAll(TYPES_BY_NAME.asInverseWithShortName());
  return map;
}
