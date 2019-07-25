public static Map<String,String> properties(){
  Map<String,Settings> groups=settings().getGroups(mode() + ".datasources");
  Settings mysqlSetting=groups.get("mysql");
  return properties(mysqlSetting);
}
