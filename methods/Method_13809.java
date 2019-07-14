/** 
 * @param request
 * @return
 */
protected DatabaseConfiguration getJdbcConfiguration(HttpServletRequest request){
  DatabaseConfiguration jdbcConfig=new DatabaseConfiguration();
  jdbcConfig.setConnectionName(request.getParameter("connectionName"));
  jdbcConfig.setDatabaseType(request.getParameter("databaseType"));
  jdbcConfig.setDatabaseHost(request.getParameter("databaseServer"));
  String dbPort=request.getParameter("databasePort");
  if (dbPort != null) {
    try {
      jdbcConfig.setDatabasePort(Integer.parseInt(dbPort));
    }
 catch (    NumberFormatException nfe) {
    }
  }
  jdbcConfig.setDatabaseUser(request.getParameter("databaseUser"));
  jdbcConfig.setDatabasePassword(request.getParameter("databasePassword"));
  jdbcConfig.setDatabaseName(request.getParameter("initialDatabase"));
  jdbcConfig.setDatabaseSchema(request.getParameter("initialSchema"));
  if (logger.isDebugEnabled()) {
    logger.debug("JDBC Configuration: {}",jdbcConfig);
  }
  return jdbcConfig;
}
