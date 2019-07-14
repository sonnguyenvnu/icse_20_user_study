/** 
 * Remove client transport from reverse map by channel key
 * @param channelKey channel key
 */
public static void removeReverseClientTransport(String channelKey){
  if (REVERSE_CLIENT_TRANSPORT_MAP != null) {
    REVERSE_CLIENT_TRANSPORT_MAP.remove(channelKey);
  }
}
