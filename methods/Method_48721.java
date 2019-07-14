public void commit(final Collection<InternalRelation> addedRelations,final Collection<InternalRelation> deletedRelations,final StandardJanusGraphTx tx){
  if (addedRelations.isEmpty() && deletedRelations.isEmpty())   return;
  log.debug("Saving transaction. Added {}, removed {}",addedRelations.size(),deletedRelations.size());
  if (!tx.getConfiguration().hasCommitTime())   tx.getConfiguration().setCommitTime(times.getTime());
  final Instant txTimestamp=tx.getConfiguration().getCommitTime();
  final long transactionId=txCounter.incrementAndGet();
  if (!tx.getConfiguration().hasAssignIDsImmediately())   idAssigner.assignIDs(addedRelations);
  BackendTransaction mutator=tx.getTxHandle();
  final boolean acquireLocks=tx.getConfiguration().hasAcquireLocks();
  final boolean hasTxIsolation=backend.getStoreFeatures().hasTxIsolation();
  final boolean logTransaction=config.hasLogTransactions() && !tx.getConfiguration().hasEnabledBatchLoading();
  final KCVSLog txLog=logTransaction ? backend.getSystemTxLog() : null;
  final TransactionLogHeader txLogHeader=new TransactionLogHeader(transactionId,txTimestamp,times);
  ModificationSummary commitSummary;
  try {
    if (logTransaction) {
      Preconditions.checkNotNull(txLog,"Transaction log is null");
      txLog.add(txLogHeader.serializeModifications(serializer,LogTxStatus.PRECOMMIT,tx,addedRelations,deletedRelations),txLogHeader.getLogKey());
    }
    boolean hasSchemaElements=!Iterables.isEmpty(Iterables.filter(deletedRelations,SCHEMA_FILTER)) || !Iterables.isEmpty(Iterables.filter(addedRelations,SCHEMA_FILTER));
    Preconditions.checkArgument(!hasSchemaElements || (!tx.getConfiguration().hasEnabledBatchLoading() && acquireLocks),"Attempting to create schema elements in inconsistent state");
    if (hasSchemaElements && !hasTxIsolation) {
      final BackendTransaction schemaMutator=openBackendTransaction(tx);
      try {
        commitSummary=prepareCommit(addedRelations,deletedRelations,SCHEMA_FILTER,schemaMutator,tx,acquireLocks);
        assert commitSummary.hasModifications && !commitSummary.has2iModifications;
      }
 catch (      Throwable e) {
        schemaMutator.rollback();
        throw e;
      }
      try {
        schemaMutator.commit();
      }
 catch (      Throwable e) {
        log.error("Could not commit transaction [" + transactionId + "] due to storage exception in system-commit",e);
        throw e;
      }
    }
    commitSummary=prepareCommit(addedRelations,deletedRelations,hasTxIsolation ? NO_FILTER : NO_SCHEMA_FILTER,mutator,tx,acquireLocks);
    if (commitSummary.hasModifications) {
      String logTxIdentifier=tx.getConfiguration().getLogIdentifier();
      boolean hasSecondaryPersistence=logTxIdentifier != null || commitSummary.has2iModifications;
      if (logTransaction) {
        txLog.add(txLogHeader.serializePrimary(serializer,hasSecondaryPersistence ? LogTxStatus.PRIMARY_SUCCESS : LogTxStatus.COMPLETE_SUCCESS),txLogHeader.getLogKey(),mutator.getTxLogPersistor());
      }
      try {
        mutator.commitStorage();
      }
 catch (      Throwable e) {
        log.error("Could not commit transaction [" + transactionId + "] due to storage exception in commit",e);
        throw e;
      }
      if (hasSecondaryPersistence) {
        LogTxStatus status=LogTxStatus.SECONDARY_SUCCESS;
        Map<String,Throwable> indexFailures=ImmutableMap.of();
        boolean userlogSuccess=true;
        try {
          indexFailures=mutator.commitIndexes();
          if (!indexFailures.isEmpty()) {
            status=LogTxStatus.SECONDARY_FAILURE;
            for (            Map.Entry<String,Throwable> entry : indexFailures.entrySet()) {
              log.error("Error while committing index mutations for transaction [" + transactionId + "] on index: " + entry.getKey(),entry.getValue());
            }
          }
          if (logTxIdentifier != null) {
            try {
              userlogSuccess=false;
              final Log userLog=backend.getUserLog(logTxIdentifier);
              Future<Message> env=userLog.add(txLogHeader.serializeModifications(serializer,LogTxStatus.USER_LOG,tx,addedRelations,deletedRelations));
              if (env.isDone()) {
                try {
                  env.get();
                }
 catch (                ExecutionException ex) {
                  throw ex.getCause();
                }
              }
              userlogSuccess=true;
            }
 catch (            Throwable e) {
              status=LogTxStatus.SECONDARY_FAILURE;
              log.error("Could not user-log committed transaction [" + transactionId + "] to " + logTxIdentifier,e);
            }
          }
        }
  finally {
          if (logTransaction) {
            try {
              txLog.add(txLogHeader.serializeSecondary(serializer,status,indexFailures,userlogSuccess),txLogHeader.getLogKey());
            }
 catch (            Throwable e) {
              log.error("Could not tx-log secondary persistence status on transaction [" + transactionId + "]",e);
            }
          }
        }
      }
 else {
        mutator.commitIndexes();
      }
    }
 else {
      mutator.commit();
    }
  }
 catch (  Throwable e) {
    log.error("Could not commit transaction [" + transactionId + "] due to exception",e);
    try {
      mutator.rollback();
    }
 catch (    Throwable e2) {
      log.error("Could not roll-back transaction [" + transactionId + "] after failure due to exception",e2);
    }
    if (e instanceof RuntimeException)     throw (RuntimeException)e;
 else     throw new JanusGraphException("Unexpected exception",e);
  }
}
