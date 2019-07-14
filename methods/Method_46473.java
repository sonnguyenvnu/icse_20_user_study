@Override public DTXPropagationState resolvePropagationState(TxTransactionInfo txTransactionInfo) throws TransactionException {
  if (DTXLocalContext.cur().isInGroup()) {
    log.info("SILENT_JOIN group!");
    return DTXPropagationState.SILENT_JOIN;
  }
  if (txTransactionInfo.isTransactionStart()) {
    if (DTXPropagation.SUPPORTS.equals(txTransactionInfo.getPropagation())) {
      return DTXPropagationState.NON;
    }
    return DTXPropagationState.CREATE;
  }
  return DTXPropagationState.JOIN;
}
