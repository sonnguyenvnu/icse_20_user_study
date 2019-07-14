@Override public Long getSchemaId(final String schemaName){
  ConcurrentMap<String,Long> types=typeNames;
  Long id;
  if (types == null) {
    id=typeNamesBackup.getIfPresent(schemaName);
    if (id == null) {
      id=retriever.retrieveSchemaByName(schemaName);
      if (id != null) {
        typeNamesBackup.put(schemaName,id);
      }
    }
  }
 else {
    id=types.get(schemaName);
    if (id == null) {
      if (types.size() > maxCachedTypes) {
        typeNames=null;
        return getSchemaId(schemaName);
      }
 else {
        id=retriever.retrieveSchemaByName(schemaName);
        if (id != null) {
          types.put(schemaName,id);
        }
      }
    }
  }
  return id;
}
