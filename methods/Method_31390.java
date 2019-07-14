/** 
 * If the TNS_ADMIN environment variable is set, enable tnsnames.ora support for the Oracle JDBC driver. See http://www.orafaq.com/wiki/TNS_ADMIN
 */
public static void enableTnsnamesOraSupport(){
  String tnsAdminEnvVar=System.getenv("TNS_ADMIN");
  String tnsAdminSysProp=System.getProperty(ORACLE_NET_TNS_ADMIN);
  if (StringUtils.hasLength(tnsAdminEnvVar) && tnsAdminSysProp == null) {
    System.setProperty(ORACLE_NET_TNS_ADMIN,tnsAdminEnvVar);
  }
}
