private static GenericRecord rewrite(GenericRecord record,Schema schemaWithFields,Schema newSchema){
  GenericRecord newRecord=new GenericData.Record(newSchema);
  for (  Schema.Field f : schemaWithFields.getFields()) {
    newRecord.put(f.name(),record.get(f.name()));
  }
  if (!GenericData.get().validate(newSchema,newRecord)) {
    throw new SchemaCompatabilityException("Unable to validate the rewritten record " + record + " against schema " + newSchema);
  }
  return newRecord;
}
