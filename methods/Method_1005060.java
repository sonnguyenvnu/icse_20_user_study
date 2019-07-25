@Override public void seek(final Range range,final Collection<ByteSequence> columnFamilies,final boolean inclusive) throws IOException {
  final Range seekRange=IteratorUtil.maximizeStartKeyTimeStamp(range);
  super.seek(seekRange,columnFamilies,inclusive);
  findTop();
  if (null != range.getStartKey()) {
    while (hasTop() && getTopKey().equals(range.getStartKey(),PartialKey.ROW_COLFAM) && getTopKey().getTimestamp() > range.getStartKey().getTimestamp()) {
      next();
    }
    while (hasTop() && range.beforeStartKey(getTopKey())) {
      next();
    }
  }
}
