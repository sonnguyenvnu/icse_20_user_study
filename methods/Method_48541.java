public String getDefaultFieldName(final PropertyKey key,final Parameter[] parameters,final String indexName){
  Preconditions.checkArgument(!ParameterType.MAPPED_NAME.hasParameter(parameters),"A field name mapping has been specified for key: %s",key);
  Preconditions.checkArgument(containsIndex(indexName),"Unknown backing index: %s",indexName);
  final String fieldname=configuration.get(INDEX_NAME_MAPPING,indexName) ? key.name() : keyID2Name(key);
  return mixedIndexes.get(indexName).mapKey2Field(fieldname,new StandardKeyInformation(key,parameters));
}
