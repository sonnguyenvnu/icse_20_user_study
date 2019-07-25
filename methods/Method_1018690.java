/** 
 * @param type type
 * @param code code
 * @return a IcmpV4Code object.
 */
public static IcmpV4Code register(IcmpV4Type type,IcmpV4Code code){
  if (registry.containsKey(type.value())) {
    return registry.get(type.value()).put(code.value(),code);
  }
 else {
    Map<Byte,IcmpV4Code> map=new HashMap<Byte,IcmpV4Code>();
    map.put(code.value(),code);
    registry.put(type.value(),map);
    return null;
  }
}
