public void workerIterationStart(JanusGraph graph,Configuration config,ScanMetrics metrics){
  this.graph=(StandardJanusGraph)graph;
  Preconditions.checkArgument(config.has(GraphDatabaseConfiguration.JOB_START_TIME),"Invalid configuration for this job. Start time is required.");
  this.jobStartTime=Instant.ofEpochMilli(config.get(GraphDatabaseConfiguration.JOB_START_TIME));
  if (indexName == null) {
    Preconditions.checkArgument(config.has(INDEX_NAME),"Need to configure the name of the index to be repaired");
    indexName=config.get(INDEX_NAME);
    indexRelationTypeName=config.get(INDEX_RELATION_TYPE);
    log.info("Read index information: name={} type={}",indexName,indexRelationTypeName);
  }
  try {
    this.managementSystem=(ManagementSystem)graph.openManagement();
    if (isGlobalGraphIndex()) {
      index=managementSystem.getGraphIndex(indexName);
    }
 else {
      indexRelationType=managementSystem.getRelationType(indexRelationTypeName);
      Preconditions.checkArgument(indexRelationType != null,"Could not find relation type: %s",indexRelationTypeName);
      index=managementSystem.getRelationIndex(indexRelationType,indexName);
    }
    Preconditions.checkArgument(index != null,"Could not find index: %s [%s]",indexName,indexRelationTypeName);
    log.info("Found index {}",indexName);
    validateIndexStatus();
    StandardTransactionBuilder txb=this.graph.buildTransaction();
    txb.commitTime(jobStartTime);
    writeTx=(StandardJanusGraphTx)txb.start();
  }
 catch (  final Exception e) {
    if (null != managementSystem && managementSystem.isOpen())     managementSystem.rollback();
    if (writeTx != null && writeTx.isOpen())     writeTx.rollback();
    metrics.incrementCustom(FAILED_TX);
    throw new JanusGraphException(e.getMessage(),e);
  }
}
