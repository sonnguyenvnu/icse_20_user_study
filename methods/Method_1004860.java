@Override public void apply(Schema schema) throws InvalidSchemaError {
  Database oldDatabase=schema.findDatabaseOrThrow(this.database);
  Table table=oldDatabase.findTableOrThrow(this.table);
  Database newDatabase;
  if (this.database.equals(newTable.database))   newDatabase=oldDatabase;
 else   newDatabase=schema.findDatabaseOrThrow(newTable.database);
  oldDatabase.removeTable(this.table);
  newDatabase.addTable(newTable);
}
