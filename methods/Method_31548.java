/** 
 * Detect the default connection properties for this url.
 * @param url The Jdbc url.
 * @return The properties.
 */
private Properties detectPropsForUrl(String url){
  Properties result=new Properties();
  if (url.startsWith(ORACLE_JDBC_URL_PREFIX)) {
    String osUser=System.getProperty("user.name");
    result.put("v$session.osuser",osUser.substring(0,Math.min(osUser.length(),30)));
    result.put("v$session.program",APPLICATION_NAME);
    result.put("oracle.net.keepAlive","true");
  }
 else   if (url.startsWith(SQLSERVER_JDBC_URL_PREFIX)) {
    result.put("applicationName",APPLICATION_NAME);
  }
 else   if (url.startsWith(POSTGRESQL_JDBC_URL_PREFIX)) {
    result.put("ApplicationName",APPLICATION_NAME);
  }
 else   if (url.startsWith(MYSQL_JDBC_URL_PREFIX) || url.startsWith(MARIADB_JDBC_URL_PREFIX)) {
    result.put("connectionAttributes","program_name:" + APPLICATION_NAME);
  }
 else   if (url.startsWith(DB2_JDBC_URL_PREFIX)) {
    result.put("clientProgramName",APPLICATION_NAME);
    result.put("retrieveMessagesFromServerOnGetMessage","true");
  }
 else   if (url.startsWith(SYBASE_JDBC_URL_PREFIX)) {
    result.put("APPLICATIONNAME",APPLICATION_NAME);
  }
 else   if (url.startsWith(SAPHANA_JDBC_URL_PREFIX)) {
    result.put("SESSIONVARIABLE:APPLICATION",APPLICATION_NAME);
  }
  return result;
}
