@Override public int notifyGroup(String groupId,int transactionState) throws RpcException, LcnBusinessException {
  NotifyGroupParams notifyGroupParams=new NotifyGroupParams();
  notifyGroupParams.setGroupId(groupId);
  notifyGroupParams.setState(transactionState);
  MessageDto messageDto=request0(MessageCreator.notifyGroup(notifyGroupParams),clientConfig.getTmRpcTimeout() * clientConfig.getChainLevel());
  if (!MessageUtils.statusOk(messageDto)) {
    throw new LcnBusinessException(messageDto.loadBean(Throwable.class));
  }
  return messageDto.loadBean(Integer.class);
}
