/** 
 * Configures a connection.
 * @param conn The connection to configure.
 * @throws SQLException
 */
public void apply(Connection conn) throws SQLException {
  HashSet<String> pragmaParams=new HashSet<String>();
  for (  Pragma each : Pragma.values()) {
    pragmaParams.add(each.pragmaName);
  }
  pragmaParams.remove(Pragma.OPEN_MODE.pragmaName);
  pragmaParams.remove(Pragma.SHARED_CACHE.pragmaName);
  pragmaParams.remove(Pragma.LOAD_EXTENSION.pragmaName);
  pragmaParams.remove(Pragma.DATE_PRECISION.pragmaName);
  pragmaParams.remove(Pragma.DATE_CLASS.pragmaName);
  pragmaParams.remove(Pragma.DATE_STRING_FORMAT.pragmaName);
  pragmaParams.remove(Pragma.PASSWORD.pragmaName);
  pragmaParams.remove(Pragma.HEXKEY_MODE.pragmaName);
  Statement stat=conn.createStatement();
  try {
    if (pragmaTable.containsKey(Pragma.PASSWORD.pragmaName)) {
      String password=pragmaTable.getProperty(Pragma.PASSWORD.pragmaName);
      if (password != null && !password.isEmpty()) {
        String hexkeyMode=pragmaTable.getProperty(Pragma.HEXKEY_MODE.pragmaName);
        String passwordPragma;
        if (HexKeyMode.SSE.name().equalsIgnoreCase(hexkeyMode)) {
          passwordPragma="pragma hexkey = '%s'";
        }
 else         if (HexKeyMode.SQLCIPHER.name().equalsIgnoreCase(hexkeyMode)) {
          passwordPragma="pragma key = \"x'%s'\"";
        }
 else {
          passwordPragma="pragma key = '%s'";
        }
        stat.execute(String.format(passwordPragma,password.replace("'","''")));
        stat.execute("select 1 from sqlite_master");
      }
    }
    for (    Object each : pragmaTable.keySet()) {
      String key=each.toString();
      if (!pragmaParams.contains(key)) {
        continue;
      }
      String value=pragmaTable.getProperty(key);
      if (value != null) {
        stat.execute(String.format("pragma %s=%s",key,value));
      }
    }
  }
  finally {
    if (stat != null) {
      stat.close();
    }
  }
}
