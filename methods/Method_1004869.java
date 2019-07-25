public Schema capture() throws SQLException {
  LOGGER.debug("Capturing schemas...");
  ArrayList<Database> databases=new ArrayList<>();
  String dbCaptureQuery="SELECT SCHEMA_NAME, DEFAULT_CHARACTER_SET_NAME FROM INFORMATION_SCHEMA.SCHEMATA";
  if (includeDatabases.size() > 0) {
    dbCaptureQuery+=" WHERE SCHEMA_NAME IN " + Sql.inListSQL(includeDatabases.size());
  }
  dbCaptureQuery+=" ORDER BY SCHEMA_NAME";
  PreparedStatement statement=connection.prepareStatement(dbCaptureQuery);
  Sql.prepareInList(statement,1,includeDatabases);
  ResultSet rs=statement.executeQuery();
  while (rs.next()) {
    String dbName=rs.getString("SCHEMA_NAME");
    String charset=rs.getString("DEFAULT_CHARACTER_SET_NAME");
    if (IGNORED_DATABASES.contains(dbName))     continue;
    Database db=new Database(dbName,charset);
    databases.add(db);
  }
  rs.close();
  int size=databases.size();
  LOGGER.debug("Starting schema capture of " + size + " databases...");
  int counter=1;
  for (  Database db : databases) {
    LOGGER.debug(counter + "/" + size + " Capturing " + db.getName() + "...");
    captureDatabase(db);
    counter++;
  }
  LOGGER.debug(size + " database schemas captured!");
  return new Schema(databases,captureDefaultCharset(),this.sensitivity);
}
