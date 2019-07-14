public static List<DatabaseConfiguration> decryptAll(List<DatabaseConfiguration> savedConnections){
  List<DatabaseConfiguration> dbConfigs=new ArrayList<DatabaseConfiguration>(savedConnections.size());
  for (  DatabaseConfiguration d : savedConnections) {
    d.setDatabasePassword(decrypt(d.getDatabasePassword()));
    dbConfigs.add(d);
  }
  return dbConfigs;
}
