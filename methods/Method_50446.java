private void executeHandler(final boolean success,final HmilyTransaction currentTransaction,final List<HmilyParticipant> failList){
  HmilyTransactionGuavaCacheManager.getInstance().removeByKey(currentTransaction.getTransId());
  if (success) {
    deleteTransaction(currentTransaction);
  }
 else {
    currentTransaction.setHmilyParticipants(failList);
    updateParticipant(currentTransaction);
    throw new HmilyRuntimeException(failList.toString());
  }
}
