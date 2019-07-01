protected BKLogSegmentWriter _XXXXX_(long txId,boolean bestEffort,boolean allowMaxTxID) throws IOException {
  return Utils.ioResult(asyncStartLogSegment(txId,bestEffort,allowMaxTxID));
}