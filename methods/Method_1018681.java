/** 
 * @param type type
 * @return a ArpHardwareType object.
 */
public static ArpHardwareType register(ArpHardwareType type){
  return registry.put(type.value(),type);
}
