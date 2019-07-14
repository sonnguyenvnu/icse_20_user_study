/** 
 * ??????????????????????
 * @param magicHeadBytes ?????
 * @return ??
 */
public static Protocol adaptiveProtocol(byte[] magicHeadBytes){
  for (  Protocol protocol : TYPE_PROTOCOL_MAP.values()) {
    if (protocol.protocolInfo().isMatchMagic(magicHeadBytes)) {
      return protocol;
    }
  }
  return null;
}
