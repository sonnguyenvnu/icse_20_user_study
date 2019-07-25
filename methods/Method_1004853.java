@Override public ResolvedDatabaseCreate resolve(Schema schema) throws InvalidSchemaError {
  if (ifNotExists && schema.hasDatabase(database))   return null;
  String chset;
  if (this.charset == null)   chset=schema.getCharset();
 else   chset=this.charset;
  return new ResolvedDatabaseCreate(database,chset);
}
