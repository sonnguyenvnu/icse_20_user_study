public Object runTransaction(DTXInfo dtxInfo,BusinessCallback business) throws Throwable {
  if (Objects.isNull(DTXLocalContext.cur())) {
    DTXLocalContext.getOrNew();
  }
 else {
    return business.call();
  }
  log.debug("<---- TxLcn start ---->");
  DTXLocalContext dtxLocalContext=DTXLocalContext.getOrNew();
  TxContext txContext;
  if (globalContext.hasTxContext()) {
    txContext=globalContext.txContext();
    dtxLocalContext.setInGroup(true);
    log.debug("Unit[{}] used parent's TxContext[{}].",dtxInfo.getUnitId(),txContext.getGroupId());
  }
 else {
    txContext=globalContext.startTx();
  }
  if (Objects.nonNull(dtxLocalContext.getGroupId())) {
    dtxLocalContext.setDestroy(false);
  }
  dtxLocalContext.setUnitId(dtxInfo.getUnitId());
  dtxLocalContext.setGroupId(txContext.getGroupId());
  dtxLocalContext.setTransactionType(dtxInfo.getTransactionType());
  TxTransactionInfo info=new TxTransactionInfo();
  info.setBusinessCallback(business);
  info.setGroupId(txContext.getGroupId());
  info.setUnitId(dtxInfo.getUnitId());
  info.setPointMethod(dtxInfo.getBusinessMethod());
  info.setPropagation(dtxInfo.getTransactionPropagation());
  info.setTransactionInfo(dtxInfo.getTransactionInfo());
  info.setTransactionType(dtxInfo.getTransactionType());
  info.setTransactionStart(txContext.isDtxStart());
  try {
    return transactionServiceExecutor.transactionRunning(info);
  }
  finally {
    if (dtxLocalContext.isDestroy()) {
synchronized (txContext.getLock()) {
        txContext.getLock().notifyAll();
      }
      if (!dtxLocalContext.isInGroup()) {
        globalContext.destroyTx();
      }
      DTXLocalContext.makeNeverAppeared();
      TracingContext.tracing().destroy();
    }
    log.debug("<---- TxLcn end ---->");
  }
}
