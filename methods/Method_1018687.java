/** 
 * @param type type
 * @return a GtpV1MessageType object.
 */
public static GtpV1ExtensionHeaderType register(GtpV1ExtensionHeaderType type){
  return registry.put(type.value(),type);
}
