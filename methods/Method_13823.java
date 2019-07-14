/** 
 * @param request
 * @return
 */
private DatabaseQueryInfo getQueryInfo(HttpServletRequest request){
  DatabaseConfiguration jdbcConfig=new DatabaseConfiguration();
  jdbcConfig.setConnectionName(request.getParameter("connectionName"));
  jdbcConfig.setDatabaseType(request.getParameter("databaseType"));
  jdbcConfig.setDatabaseHost(request.getParameter("databaseServer"));
  jdbcConfig.setDatabasePort(Integer.parseInt(request.getParameter("databasePort")));
  jdbcConfig.setDatabaseUser(request.getParameter("databaseUser"));
  jdbcConfig.setDatabasePassword(request.getParameter("databasePassword"));
  jdbcConfig.setDatabaseName(request.getParameter("initialDatabase"));
  jdbcConfig.setDatabaseSchema(request.getParameter("initialSchema"));
  String query=request.getParameter("query");
  if (logger.isDebugEnabled()) {
    logger.debug("jdbcConfig::{}, query::{}",jdbcConfig,query);
  }
  if (jdbcConfig.getDatabaseHost() == null || jdbcConfig.getDatabaseName() == null || jdbcConfig.getDatabasePassword() == null || jdbcConfig.getDatabaseType() == null || jdbcConfig.getDatabaseUser() == null || query == null) {
    if (logger.isDebugEnabled()) {
      logger.debug("Missing Database Configuration::{}",jdbcConfig);
    }
    return null;
  }
  return new DatabaseQueryInfo(jdbcConfig,query);
}
