@Override public <M extends ObjectMetadata>void registerMetaDataParser(DatabaseType databaseType,ObjectMetadata.ObjectType objectType,MetaDataParser<M> parser){
  parserRepo.computeIfAbsent(databaseType,t -> new HashMap<>()).put(objectType,parser);
}
