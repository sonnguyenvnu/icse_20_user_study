@Override protected boolean doEmpty(){
  Table[] tables=allTables();
  List<String> tableNames=new ArrayList<>();
  for (  Table table : tables) {
    String tableName=table.getName();
    if (!IGNORED_SYSTEM_TABLE_NAMES.contains(tableName)) {
      tableNames.add(tableName);
    }
  }
  return tableNames.isEmpty();
}
