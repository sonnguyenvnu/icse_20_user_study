@Override public void apply(Table table,List<DeferredPositionUpdate> deferred) throws InvalidSchemaError {
  int index=position.index(table,null);
  if (index == ColumnPosition.AFTER_NOT_FOUND) {
    deferred.add(new DeferredPositionUpdate(definition.getName(),position));
    index=0;
  }
  table.addColumn(index,this.definition);
}
