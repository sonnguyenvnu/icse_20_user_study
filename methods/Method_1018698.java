/** 
 * @param type type
 * @return a EtherType object.
 */
public static LinuxSllPacketType register(LinuxSllPacketType type){
  return registry.put(type.value(),type);
}
