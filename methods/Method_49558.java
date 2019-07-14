/** 
 * Delete all rows from the given table. This method is intended only for development and testing use.
 * @param tableString
 * @param timestamp
 * @throws IOException
 */
@Override public void clearTable(String tableString,long timestamp) throws IOException {
  TableName tableName=TableName.valueOf(tableString);
  if (!adm.tableExists(tableName)) {
    log.debug("Attempted to clear table {} before it exists (noop)",tableString);
    return;
  }
  final Scan scan=new Scan();
  scan.setCacheBlocks(false);
  scan.setCaching(2000);
  scan.setTimeRange(0,Long.MAX_VALUE);
  scan.setMaxVersions(1);
  try (final Table table=adm.getConnection().getTable(tableName);final ResultScanner scanner=table.getScanner(scan)){
    final Iterator<Result> iterator=scanner.iterator();
    final int batchSize=1000;
    final List<Delete> deleteList=new ArrayList<>();
    while (iterator.hasNext()) {
      deleteList.add(new Delete(iterator.next().getRow(),timestamp));
      if (!iterator.hasNext() || deleteList.size() == batchSize) {
        table.delete(deleteList);
        deleteList.clear();
      }
    }
  }
 }
