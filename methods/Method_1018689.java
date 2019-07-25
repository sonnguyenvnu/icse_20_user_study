/** 
 * @param type type
 * @return a GtpV2MessageType object.
 */
public static GtpV2MessageType register(GtpV2MessageType type){
  return registry.put(type.value(),type);
}
