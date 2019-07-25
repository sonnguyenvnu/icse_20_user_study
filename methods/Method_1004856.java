@Override public void apply(Table table,List<DeferredPositionUpdate> deferred) throws InvalidSchemaError {
  int idx=originalIndex(table);
  table.renameColumn(idx,newName);
}
