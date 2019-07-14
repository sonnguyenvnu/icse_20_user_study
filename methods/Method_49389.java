private static EntryList fromResultSet(final ResultSet resultSet,final GetColVal<Tuple3<StaticBuffer,StaticBuffer,Row>,StaticBuffer> getter){
  final Lazy<ArrayList<Row>> lazyList=Lazy.of(() -> Lists.newArrayList(resultSet));
  return StaticArrayEntryList.ofStaticBuffer(() -> Iterator.ofAll(lazyList.get()).map(row -> Tuple.of(StaticArrayBuffer.of(row.getBytes(COLUMN_COLUMN_NAME)),StaticArrayBuffer.of(row.getBytes(VALUE_COLUMN_NAME)),row)),getter);
}
