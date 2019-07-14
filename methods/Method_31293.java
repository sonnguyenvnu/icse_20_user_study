/** 
 * @return All tables that have versioning associated with them.
 */
private List<String> generateDropVersioningStatement() throws SQLException {
  List<String> dropVersioningStatements=new ArrayList<>();
  Table[] versioningTables=findTables("select TABNAME from SYSCAT.TABLES where TEMPORALTYPE <> 'N' and TABSCHEMA = ?",name);
  for (  Table table : versioningTables) {
    dropVersioningStatements.add("ALTER TABLE " + table.toString() + " DROP VERSIONING");
  }
  return dropVersioningStatements;
}
