@Override @SuppressWarnings("unchecked") public HashSet<String> queryTMCluster() throws RpcException {
  MessageDto messageDto=new MessageDto();
  messageDto.setAction(MessageConstants.ACTION_QUERY_TM_CLUSTER);
  messageDto=request(messageDto);
  if (MessageUtils.statusOk(messageDto)) {
    return messageDto.loadBean(HashSet.class);
  }
  throw new RpcException(messageDto.loadBean(Throwable.class));
}
