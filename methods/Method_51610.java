private String init(DBURI dbURI) throws ClassNotFoundException {
  this.dburi=dbURI;
  this.returnSourceCodeObjectsStatement=dbURI.getDbType().getProperties().getProperty(GET_SOURCE_OBJECTS_STATEMENT);
  this.returnSourceCodeStatement=dbURI.getDbType().getProperties().getProperty(GET_SOURCE_CODE_STATEMENT);
  this.returnType=dbURI.getSourceCodeType();
  if (LOGGER.isLoggable(Level.FINE)) {
    LOGGER.fine("returnSourceCodeStatement=" + returnSourceCodeStatement + ", returnType=" + returnType);
  }
  String driverClass=dbURI.getDriverClass();
  String urlString=dbURI.getURL().toString();
  if (LOGGER.isLoggable(Level.FINE)) {
    LOGGER.fine("driverClass=" + driverClass + ", urlString=" + urlString);
  }
  Class.forName(driverClass);
  if (LOGGER.isLoggable(Level.FINE)) {
    LOGGER.fine("Located class for driverClass=" + driverClass);
  }
  return urlString;
}
