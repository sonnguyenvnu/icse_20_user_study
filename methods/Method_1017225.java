@Override public PreparedFetch row(final BackendKey key) throws IOException {
  final long base=key.getBase();
  final ByteBuffer k=ROW_KEY.serialize(new MetricsRowKey(key.getSeries(),base));
  return new PreparedFetch(){
    @Override public BoundStatement fetch(    int limit){
      return fetch.bind(k,Integer.MIN_VALUE,Integer.MAX_VALUE,limit);
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
      return "<Fetch Row " + key + ">";
    }
  }
;
}
