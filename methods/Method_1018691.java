/** 
 * @param type type
 * @return a IcmpV4Type object.
 */
public static IcmpV4Type register(IcmpV4Type type){
  return registry.put(type.value(),type);
}
