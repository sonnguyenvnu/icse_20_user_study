List<PreparedQuery> ranges(final Series series,final DateRange range,final String columnFamily,final BiFunction<Long,ByteString,Metric> deserializer) throws IOException {
  final List<PreparedQuery> bases=new ArrayList<>();
  final long start=base(range.getStart());
  final long end=base(range.getEnd());
  for (long base=start; base <= end; base+=PERIOD) {
    final DateRange modified=range.modify(base,base + PERIOD);
    if (modified.isEmpty()) {
      continue;
    }
    final ByteString key=rowKeySerializer.serializeMinimal(new RowKeyMinimal(RowKeyMinimal.Series.create(series),base));
    final ByteString keyEnd=ByteString.copyFrom(RowKeyUtil.calculateTheClosestNextRowKeyForPrefix(key.toByteArray()));
    final ByteString startKey=serializeOffset(offset(modified.start()));
    final ByteString endKey=serializeOffset(offset(modified.end()));
    bases.add(new PreparedQuery(key,keyEnd,columnFamily,startKey,endKey,deserializer,base));
  }
  return bases;
}
