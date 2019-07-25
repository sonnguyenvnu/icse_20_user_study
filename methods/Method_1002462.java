@Override public void callback(List<String> path,DataSchema schema){
  if (schema.getType() != DataSchema.Type.RECORD) {
    return;
  }
  if (schema.getProperties().get("avro") != null) {
    return;
  }
  RecordDataSchema recordSchema=(RecordDataSchema)schema;
  for (  RecordDataSchema.Field field : recordSchema.getFields()) {
    DataSchema fieldSchema=field.getType().getDereferencedDataSchema();
    DataMap modifiedDefaultValue=modifyFieldDefaultValue(field,path);
    DataSchema modifiedSchema=modifyFieldSchema(recordSchema,field,fieldSchema,modifiedDefaultValue);
    if (modifiedSchema != null) {
      overrideUnionFieldSchemaAndDefault(field,modifiedSchema,modifiedDefaultValue);
    }
  }
}
