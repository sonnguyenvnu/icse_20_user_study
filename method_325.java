/** 
 * Close log segment.
 * @param inprogressZnodeName
 * @param logSegmentSeqNo
 * @param logSegmentId
 * @param firstTxId
 * @param lastTxId
 * @param recordCount
 * @param lastEntryId
 * @param lastSlotId
 * @throws IOException
 */
protected LogSegmentMetadata _XXXXX_(String inprogressZnodeName,long logSegmentSeqNo,long logSegmentId,long firstTxId,long lastTxId,int recordCount,long lastEntryId,long lastSlotId) throws IOException {
  CompletableFuture<LogSegmentMetadata> promise=new CompletableFuture<LogSegmentMetadata>();
  _XXXXX_(inprogressZnodeName,logSegmentSeqNo,logSegmentId,firstTxId,lastTxId,recordCount,lastEntryId,lastSlotId,promise);
  return Utils.ioResult(promise);
}