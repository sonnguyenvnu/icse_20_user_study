private Map<String,String> parseKeyFieldsForCollections(Configuration config) throws BackendException {
  final Map<String,String> keyFieldNames=new HashMap<>();
  final String[] collectionFieldStatements=config.has(KEY_FIELD_NAMES) ? config.get(KEY_FIELD_NAMES) : new String[0];
  for (  final String collectionFieldStatement : collectionFieldStatements) {
    final String[] parts=collectionFieldStatement.trim().split("=");
    if (parts.length != 2) {
      throw new PermanentBackendException("Unable to parse the collection name / key field name pair. It should be of the format collection=field");
    }
    final String collectionName=parts[0];
    final String keyFieldName=parts[1];
    keyFieldNames.put(collectionName,keyFieldName);
  }
  return keyFieldNames;
}
