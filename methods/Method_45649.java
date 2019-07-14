/** 
 * ?????????
 * @param protocolInfo ??????
 */
protected static synchronized void registerAdaptiveProtocol(ProtocolInfo protocolInfo){
  maxMagicOffset=Math.max(maxMagicOffset,protocolInfo.magicFieldOffset() + protocolInfo.magicFieldLength());
}
