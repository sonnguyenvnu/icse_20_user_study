private Schema[] prepareSchemas(Database database){
  String[] schemaNames=configuration.getSchemas();
  if (schemaNames.length == 0) {
    Schema currentSchema=database.getMainConnection().getCurrentSchema();
    if (currentSchema == null) {
      throw new FlywayException("Unable to determine schema for the schema history table." + " Set a default schema for the connection or specify one using the schemas property!");
    }
    schemaNames=new String[]{currentSchema.getName()};
  }
  if (schemaNames.length == 1) {
    LOG.debug("Schema: " + schemaNames[0]);
  }
 else {
    LOG.debug("Schemas: " + StringUtils.arrayToCommaDelimitedString(schemaNames));
  }
  Schema[] schemas=new Schema[schemaNames.length];
  for (int i=0; i < schemaNames.length; i++) {
    schemas[i]=database.getMainConnection().getSchema(schemaNames[i]);
  }
  return schemas;
}
