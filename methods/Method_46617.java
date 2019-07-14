@Override public void reportInvalidTM(HashSet<String> invalidTMSet) throws RpcException {
  MessageDto messageDto=new MessageDto();
  messageDto.setAction(MessageConstants.ACTION_CLEAN_INVALID_TM);
  messageDto.setData(invalidTMSet);
  messageDto=request(messageDto);
  if (!MessageUtils.statusOk(messageDto)) {
    throw new RpcException(messageDto.loadBean(Throwable.class));
  }
}
