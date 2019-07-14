/** 
 * Add a new Saved JDBC connection configuration
 */
@Override public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  if (logger.isDebugEnabled()) {
    logger.debug("doPost Connection: {}",request.getParameter("connectionName"));
  }
  DatabaseConfiguration jdbcConfig=getJdbcConfiguration(request);
  response.setCharacterEncoding("UTF-8");
  response.setHeader("Content-Type","application/json");
  if (jdbcConfig.getConnectionName() == null) {
    response.sendError(HttpStatus.SC_BAD_REQUEST,"Connection Name is Required!");
    response.flushBuffer();
    return;
  }
  DatabaseConfiguration savedConn=DatabaseUtils.getSavedConnection(jdbcConfig.getConnectionName());
  if (savedConn != null) {
    response.sendError(HttpStatus.SC_BAD_REQUEST,"Connection with name " + jdbcConfig.getConnectionName() + " already exists!");
    response.flushBuffer();
    return;
  }
  if (jdbcConfig.getDatabasePassword() != null) {
    jdbcConfig.setDatabasePassword(DatabaseUtils.encrypt(jdbcConfig.getDatabasePassword()));
  }
  DatabaseUtils.addToSavedConnections(jdbcConfig);
  try {
    response.setCharacterEncoding("UTF-8");
    response.setHeader("Content-Type","application/json");
    writeSavedConnectionResponse(response);
  }
 catch (  Exception e) {
    logger.error("Exception while loading settings {}",e);
  }
}
