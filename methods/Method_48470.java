public SchemaCache getTypeCache(SchemaCache.StoreRetrieval retriever){
  if (configuration.get(BASIC_METRICS))   return new MetricInstrumentedSchemaCache(retriever);
 else   return new StandardSchemaCache(retriever);
}
