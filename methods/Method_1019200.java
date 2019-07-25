/** 
 * Ensures compaction instant is in expected state and performs Compaction for the workload stored in instant-time
 * @param compactionInstantTime Compaction Instant Time
 */
private JavaRDD<WriteStatus> compact(String compactionInstantTime,boolean autoCommit) throws IOException {
  HoodieTableMetaClient metaClient=new HoodieTableMetaClient(jsc.hadoopConfiguration(),config.getBasePath(),true);
  HoodieTable<T> table=HoodieTable.getHoodieTable(metaClient,config,jsc);
  HoodieTimeline pendingCompactionTimeline=metaClient.getActiveTimeline().filterPendingCompactionTimeline();
  HoodieInstant inflightInstant=HoodieTimeline.getCompactionInflightInstant(compactionInstantTime);
  if (pendingCompactionTimeline.containsInstant(inflightInstant)) {
    rollbackInflightCompaction(inflightInstant,table);
    metaClient=new HoodieTableMetaClient(jsc.hadoopConfiguration(),config.getBasePath(),true);
    table=HoodieTable.getHoodieTable(metaClient,config,jsc);
    pendingCompactionTimeline=metaClient.getActiveTimeline().filterPendingCompactionTimeline();
  }
  HoodieInstant instant=HoodieTimeline.getCompactionRequestedInstant(compactionInstantTime);
  if (pendingCompactionTimeline.containsInstant(instant)) {
    return runCompaction(instant,metaClient.getActiveTimeline(),autoCommit);
  }
 else {
    throw new IllegalStateException("No Compaction request available at " + compactionInstantTime + " to run compaction");
  }
}
