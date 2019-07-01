/** 
 * Ledger metadata tells us how many records are in each completed segment, but for the first and last segments we may have to crack open the entry and count. For the first entry, we need to do so because beginDLSN may be an interior entry. For the last entry, if it is inprogress, we need to recover it and find the last user entry.
 */
private CompletableFuture<Long> _XXXXX_(final LogSegmentMetadata ledger,final DLSN beginDLSN){
  if (ledger.isInProgress() && ledger.isDLSNinThisSegment(beginDLSN)) {
    return asyncReadLastUserRecord(ledger).thenCompose((Function<LogRecordWithDLSN,CompletableFuture<Long>>)endRecord -> {
      if (null != endRecord) {
        return _XXXXX_(ledger,beginDLSN,endRecord.getLastPositionWithinLogSegment());
      }
 else {
        return FutureUtils.value((long)0);
      }
    }
);
  }
 else   if (ledger.isInProgress()) {
    return asyncReadLastUserRecord(ledger).thenApply(endRecord -> {
      if (null != endRecord) {
        return (long)endRecord.getLastPositionWithinLogSegment();
      }
 else {
        return (long)0;
      }
    }
);
  }
 else   if (ledger.isDLSNinThisSegment(beginDLSN)) {
    return _XXXXX_(ledger,beginDLSN,ledger.getRecordCount());
  }
 else {
    return FutureUtils.value((long)ledger.getRecordCount());
  }
}