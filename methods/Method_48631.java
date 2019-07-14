/** 
 * Poll a relation index until it has a certain  {@link SchemaStatus}, or until a configurable timeout is exceeded.
 * @return a report with information about schema state, execution duration, and the index
 */
@Override public RelationIndexStatusReport call() throws InterruptedException {
  Preconditions.checkNotNull(g,"Graph instance must not be null");
  Preconditions.checkNotNull(relationIndexName,"Index name must not be null");
  Preconditions.checkNotNull(statuses,"Target statuses must not be null");
  Preconditions.checkArgument(statuses.size() > 0,"Target statuses must include at least one status");
  RelationTypeIndex idx;
  Timer t=new Timer(TimestampProviders.MILLI).start();
  boolean timedOut;
  while (true) {
    final SchemaStatus actualStatus;
    JanusGraphManagement management=null;
    try {
      management=g.openManagement();
      idx=management.getRelationIndex(management.getRelationType(relationTypeName),relationIndexName);
      actualStatus=idx.getIndexStatus();
      LOGGER.info("Index {} (relation type {}) has status {}",relationIndexName,relationTypeName,actualStatus);
      if (statuses.contains(actualStatus)) {
        return new RelationIndexStatusReport(true,relationIndexName,relationTypeName,actualStatus,statuses,t.elapsed());
      }
    }
  finally {
      if (null != management)       management.rollback();
    }
    timedOut=null != timeout && 0 < t.elapsed().compareTo(timeout);
    if (timedOut) {
      LOGGER.info("Timed out ({}) while waiting for index {} (relation type {}) to reach status(es) {}",timeout,relationIndexName,relationTypeName,statuses);
      return new RelationIndexStatusReport(false,relationIndexName,relationTypeName,actualStatus,statuses,t.elapsed());
    }
    Thread.sleep(poll.toMillis());
  }
}
