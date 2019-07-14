@Override public int transactionState(String groupId){
  log.debug("transactionState > groupId: {}",groupId);
  List<Integer> lists=txExceptionRepository.getTransactionStateByGroupId(groupId,PageRequest.of(0,1));
  if (lists.size() <= 0) {
    return -1;
  }
  return lists.get(0);
}
