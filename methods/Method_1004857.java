@Override public void apply(Schema schema) throws InvalidSchemaError {
  if (charset == null)   return;
  Database d=schema.findDatabaseOrThrow(database);
  if (!d.getCharset().equals(charset))   d.setCharset(charset);
}
