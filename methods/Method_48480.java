@Override public void expireSchemaElement(final long schemaId){
  final long cutTypeId=(schemaId >>> SCHEMAID_BACK_SHIFT);
  ConcurrentMap<Long,EntryList> types=schemaRelations;
  if (types != null) {
    types.keySet().removeIf(key -> (key >>> SCHEMAID_TOTALFORW_SHIFT) == cutTypeId);
  }
  for (  Long key : schemaRelationsBackup.asMap().keySet()) {
    if ((key >>> SCHEMAID_TOTALFORW_SHIFT) == cutTypeId)     schemaRelationsBackup.invalidate(key);
  }
  ConcurrentMap<String,Long> names=typeNames;
  if (names != null) {
    names.entrySet().removeIf(next -> next.getValue().equals(schemaId));
  }
  for (  Map.Entry<String,Long> entry : typeNamesBackup.asMap().entrySet()) {
    if (entry.getValue().equals(schemaId))     typeNamesBackup.invalidate(entry.getKey());
  }
}
