private KeyIterator executeKeySliceQuery(@Nullable byte[] startKey,@Nullable byte[] endKey,FilterList filters,@Nullable SliceQuery columnSlice) throws BackendException {
  Scan scan=new Scan().addFamily(columnFamilyBytes);
  try {
    scan.setTimeRange(0,Long.MAX_VALUE);
  }
 catch (  IOException e) {
    throw new PermanentBackendException(e);
  }
  if (startKey != null)   scan.setStartRow(startKey);
  if (endKey != null)   scan.setStopRow(endKey);
  if (columnSlice != null) {
    filters.addFilter(getFilter(columnSlice));
  }
  TableMask table=null;
  try {
    table=cnx.getTable(tableName);
    return new RowIterator(table,table.getScanner(scan.setFilter(filters)),columnFamilyBytes);
  }
 catch (  IOException e) {
    IOUtils.closeQuietly(table);
    throw new PermanentBackendException(e);
  }
}
