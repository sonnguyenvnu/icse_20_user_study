@Override public void apply(Schema schema) throws InvalidSchemaError {
  Database d=schema.findDatabaseOrThrow(this.database);
  if (d.hasTable(this.table))   throw new InvalidSchemaError("Unexpectedly asked to create existing table " + this.table);
  d.addTable(this.def);
}
