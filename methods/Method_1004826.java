public boolean matches(RowMap row){
  return database.equalsIgnoreCase(row.getDatabase()) && table.equalsIgnoreCase(row.getTable());
}
