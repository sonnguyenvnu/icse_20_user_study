@Override public void doPut(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  if (logger.isDebugEnabled()) {
    logger.debug("databaseType::{} ",request.getParameter("databaseHost"));
  }
  DatabaseConfiguration jdbcConfig=getJdbcConfiguration(request);
  StringBuilder sb=new StringBuilder();
  boolean error=false;
  if (jdbcConfig.getConnectionName() == null) {
    sb.append("Connection Name, ");
    error=true;
  }
  if (jdbcConfig.getDatabaseHost() == null) {
    sb.append("Database Host, ");
    error=true;
  }
  if (jdbcConfig.getDatabaseUser() == null) {
    sb.append("Database User, ");
    error=true;
  }
  if (jdbcConfig.getDatabaseName() == null) {
    sb.append("Database Name, ");
    error=true;
  }
  if (error) {
    sb.append(" is missing");
    logger.debug("Connection Parameter errors::{}",sb.toString());
    response.sendError(HttpStatus.SC_BAD_REQUEST,sb.toString());
  }
  if (logger.isDebugEnabled()) {
    logger.debug("Connection Config:: {}",jdbcConfig.getConnectionName());
  }
  if (jdbcConfig.getDatabasePassword() != null) {
    jdbcConfig.setDatabasePassword(DatabaseUtils.encrypt(jdbcConfig.getDatabasePassword()));
  }
  DatabaseUtils.editSavedConnections(jdbcConfig);
  try {
    response.setCharacterEncoding("UTF-8");
    response.setHeader("Content-Type","application/json");
    writeSavedConnectionResponse(response);
  }
 catch (  Exception e) {
    logger.error("Exception while loading settings {}",e);
  }
}
