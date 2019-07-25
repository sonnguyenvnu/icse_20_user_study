/** 
 * @param type type
 * @return a IpV6OptionType object.
 */
public static IpV6OptionType register(IpV6OptionType type){
  return registry.put(type.value(),type);
}
