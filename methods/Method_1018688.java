/** 
 * @param type type
 * @return a GtpV1MessageType object.
 */
public static GtpV1MessageType register(GtpV1MessageType type){
  return registry.put(type.value(),type);
}
