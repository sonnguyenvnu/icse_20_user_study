protected long _XXXXX_(LogSegmentMetadata segment) throws IOException {
  if (!segment.isInProgress()) {
    return segment.getStartSequenceId();
  }
  long startSequenceId=DistributedLogConstants.UNASSIGNED_SEQUENCE_ID;
  if (LogSegmentMetadata.supportsSequenceId(conf.getDLLedgerMetadataLayoutVersion()) && segment.supportsSequenceId()) {
    List<LogSegmentMetadata> logSegmentDescList=getCachedLogSegments(LogSegmentMetadata.DESC_COMPARATOR);
    startSequenceId=DLUtils.computeStartSequenceId(logSegmentDescList,segment);
  }
  return startSequenceId;
}