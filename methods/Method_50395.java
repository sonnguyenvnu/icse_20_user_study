/** 
 * check db type.
 * @param driverClassName driverClassName
 * @return mysql sqlserver oracle postgresql.
 */
public static String buildByDriverClassName(final String driverClassName){
  String dbType=null;
  if (driverClassName.contains(CommonConstant.DB_MYSQL)) {
    dbType=CommonConstant.DB_MYSQL;
  }
 else   if (driverClassName.contains(CommonConstant.DB_SQLSERVER)) {
    dbType=CommonConstant.DB_SQLSERVER;
  }
 else   if (driverClassName.contains(CommonConstant.DB_ORACLE)) {
    dbType=CommonConstant.DB_ORACLE;
  }
 else   if (driverClassName.contains(CommonConstant.DB_POSTGRESQL)) {
    dbType=CommonConstant.DB_POSTGRESQL;
  }
  return dbType;
}
