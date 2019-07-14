/** 
 * ???
 * @param messageDto            ????
 * @param whenNonManagerMessage ????
 * @return MessageDto
 * @throws RpcException RpcException
 */
private MessageDto request(MessageDto messageDto,long timeout,String whenNonManagerMessage) throws RpcException {
  for (int i=0; i < rpcClient.loadAllRemoteKey().size() + 1; i++) {
    try {
      String remoteKey=rpcClient.loadRemoteKey();
      MessageDto result=rpcClient.request(remoteKey,messageDto,timeout);
      log.debug("request action: {}. TM[{}]",messageDto.getAction(),remoteKey);
      return result;
    }
 catch (    RpcException e) {
      if (e.getCode() == RpcException.NON_TX_MANAGER) {
        throw new RpcException(e.getCode(),whenNonManagerMessage + ". non tx-manager is alive.");
      }
    }
  }
  throw new RpcException(RpcException.NON_TX_MANAGER,whenNonManagerMessage + ". non tx-manager is alive.");
}
