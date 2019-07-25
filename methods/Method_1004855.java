@Override public void apply(Table table,List<DeferredPositionUpdate> deferred) throws InvalidSchemaError {
  table.removeColumn(originalIndex(table));
}
