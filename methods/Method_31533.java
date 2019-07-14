/** 
 * Dumps the info about all migrations into an ascii table.
 * @param migrationInfos The list of migrationInfos to dump.
 * @return The ascii table, as one big multi-line string.
 */
public static String dumpToAsciiTable(MigrationInfo[] migrationInfos){
  List<String> columns=Arrays.asList("Category","Version","Description","Type","Installed On","State");
  List<List<String>> rows=new ArrayList<>();
  for (  MigrationInfo migrationInfo : migrationInfos) {
    List<String> row=Arrays.asList(getCategory(migrationInfo),getVersionStr(migrationInfo),migrationInfo.getDescription(),migrationInfo.getType().name(),DateUtils.formatDateAsIsoString(migrationInfo.getInstalledOn()),migrationInfo.getState().getDisplayName());
    rows.add(row);
  }
  return new AsciiTable(columns,rows,true,"","No migrations found").render();
}
