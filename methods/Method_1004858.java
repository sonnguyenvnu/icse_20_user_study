@Override public void apply(Schema schema) throws InvalidSchemaError {
  if (schema.hasDatabase(database))   throw new InvalidSchemaError("Unexpectedly asked to create existing database " + database);
  schema.addDatabase(new Database(database,charset));
}
