/** 
 * ?????IO??????????????????
 * @param config ?????
 */
public static void closeServerIoEventLoopGroup(ServerTransportConfig config){
  EventLoopGroup ioGroup=serverIoGroups.get(config.getProtocolType());
  if (closeEventLoopGroupIfNoRef(ioGroup)) {
    serverIoGroups.remove(config.getProtocolType());
  }
}
