@Override public void apply(Schema schema) throws InvalidSchemaError {
  Database d=schema.findDatabaseOrThrow(database);
  schema.getDatabases().remove(d);
}
