@Override public int askTransactionState(String groupId,String unitId) throws RpcException {
  MessageDto messageDto=request(MessageCreator.askTransactionState(groupId,unitId));
  if (MessageUtils.statusOk(messageDto)) {
    return messageDto.loadBean(Integer.class);
  }
  return -1;
}
