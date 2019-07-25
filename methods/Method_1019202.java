private List<WriteStatus> compact(HoodieCopyOnWriteTable hoodieCopyOnWriteTable,HoodieTableMetaClient metaClient,HoodieWriteConfig config,CompactionOperation operation,String commitTime) throws IOException {
  FileSystem fs=metaClient.getFs();
  Schema readerSchema=HoodieAvroUtils.addMetadataFields(new Schema.Parser().parse(config.getSchema()));
  log.info("Compacting base " + operation.getDataFilePath() + " with delta files " + operation.getDeltaFilePaths() + " for commit " + commitTime);
  String maxInstantTime=metaClient.getActiveTimeline().getTimelineOfActions(Sets.newHashSet(HoodieTimeline.COMMIT_ACTION,HoodieTimeline.ROLLBACK_ACTION,HoodieTimeline.DELTA_COMMIT_ACTION)).filterCompletedInstants().lastInstant().get().getTimestamp();
  log.info("MaxMemoryPerCompaction => " + config.getMaxMemoryPerCompaction());
  HoodieMergedLogRecordScanner scanner=new HoodieMergedLogRecordScanner(fs,metaClient.getBasePath(),operation.getDeltaFilePaths(),readerSchema,maxInstantTime,config.getMaxMemoryPerCompaction(),config.getCompactionLazyBlockReadEnabled(),config.getCompactionReverseLogReadEnabled(),config.getMaxDFSStreamBufferSize(),config.getSpillableMapBasePath());
  if (!scanner.iterator().hasNext()) {
    return Lists.<WriteStatus>newArrayList();
  }
  Option<HoodieDataFile> oldDataFileOpt=operation.getBaseFile();
  Iterator<List<WriteStatus>> result;
  if (oldDataFileOpt.isPresent()) {
    result=hoodieCopyOnWriteTable.handleUpdate(commitTime,operation.getFileId(),scanner.getRecords(),oldDataFileOpt.get());
  }
 else {
    result=hoodieCopyOnWriteTable.handleInsert(commitTime,operation.getPartitionPath(),operation.getFileId(),scanner.iterator());
  }
  Iterable<List<WriteStatus>> resultIterable=() -> result;
  return StreamSupport.stream(resultIterable.spliterator(),false).flatMap(Collection::stream).peek(s -> {
    s.getStat().setTotalUpdatedRecordsCompacted(scanner.getNumMergedRecordsInLog());
    s.getStat().setTotalLogFilesCompacted(scanner.getTotalLogFiles());
    s.getStat().setTotalLogRecords(scanner.getTotalLogRecords());
    s.getStat().setPartitionPath(operation.getPartitionPath());
    s.getStat().setTotalLogSizeCompacted(operation.getMetrics().get(CompactionStrategy.TOTAL_LOG_FILE_SIZE).longValue());
    s.getStat().setTotalLogBlocks(scanner.getTotalLogBlocks());
    s.getStat().setTotalCorruptLogBlock(scanner.getTotalCorruptBlocks());
    s.getStat().setTotalRollbackBlocks(scanner.getTotalRollbacks());
    RuntimeStats runtimeStats=new RuntimeStats();
    runtimeStats.setTotalScanTime(scanner.getTotalTimeTakenToReadAndMergeBlocks());
    s.getStat().setRuntimeStats(runtimeStats);
  }
).collect(toList());
}
