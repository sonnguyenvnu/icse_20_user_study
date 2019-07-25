@Override public List<PreparedFetch> ranges(final Series series,final DateRange range) throws IOException {
  final List<PreparedFetch> bases=new ArrayList<>();
  final long start=calculateBaseTimestamp(range.getStart());
  final long end=calculateBaseTimestamp(range.getEnd());
  for (long currentBase=start; currentBase <= end; currentBase+=MAX_WIDTH) {
    final DateRange modified=range.modify(currentBase,currentBase + MAX_WIDTH);
    if (modified.isEmpty()) {
      continue;
    }
    final ByteBuffer key=ROW_KEY.serialize(new MetricsRowKey(series,currentBase));
    final int startColumn=calculateColumnKey(modified.start());
    final int endColumn=calculateColumnKey(modified.end());
    final long base=currentBase;
    bases.add(new PreparedFetch(){
      @Override public BoundStatement fetch(      int limit){
        return fetch.bind(key,startColumn,endColumn,limit);
      }
      @Override public Transform<Row,Point> converter(){
        return row -> {
          final long timestamp=calculateAbsoluteTimestamp(base,row.getInt(0));
          final double value=row.getDouble(1);
          return new Point(timestamp,value);
        }
;
      }
      @Override public String toString(){
        return modified.toString();
      }
    }
);
  }
  return bases;
}
