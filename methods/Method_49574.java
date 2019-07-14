@Override public void mutateMany(Map<String,Map<StaticBuffer,KCVMutation>> mutations,StoreTransaction txh) throws BackendException {
  final MaskedTimestamp commitTime=new MaskedTimestamp(txh);
  final Map<StaticBuffer,Pair<List<Put>,Delete>> commandsPerKey=convertToCommands(mutations,commitTime.getAdditionTime(times),commitTime.getDeletionTime(times));
  final List<Row> batch=new ArrayList<>(commandsPerKey.size());
  for (  Pair<List<Put>,Delete> commands : commandsPerKey.values()) {
    if (commands.getFirst() != null && !commands.getFirst().isEmpty())     batch.addAll(commands.getFirst());
    if (commands.getSecond() != null)     batch.add(commands.getSecond());
  }
  try {
    TableMask table=null;
    try {
      table=cnx.getTable(tableName);
      table.batch(batch,new Object[batch.size()]);
    }
  finally {
      IOUtils.closeQuietly(table);
    }
  }
 catch (  IOException|InterruptedException e) {
    throw new TemporaryBackendException(e);
  }
  sleepAfterWrite(txh,commitTime);
}
