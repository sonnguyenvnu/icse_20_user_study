/** 
 * @param type type
 * @return a IcmpV6Type object.
 */
public static IcmpV6Type register(IcmpV6Type type){
  return registry.put(type.value(),type);
}
