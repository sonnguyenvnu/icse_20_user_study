/** 
 * @param type type
 * @return a IpV6NeighborDiscoveryOptionType object.
 */
public static IpV6NeighborDiscoveryOptionType register(IpV6NeighborDiscoveryOptionType type){
  return registry.put(type.value(),type);
}
