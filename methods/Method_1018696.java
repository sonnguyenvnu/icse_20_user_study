/** 
 * @param number number
 * @return a IpV6RoutingHeaderType object.
 */
public static IpV6RoutingType register(IpV6RoutingType number){
  return registry.put(number.value(),number);
}
