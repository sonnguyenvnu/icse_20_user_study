private String getDbServiceException(Exception ex){
  String message="";
  if (ex instanceof DatabaseServiceException) {
    DatabaseServiceException dbEx=(DatabaseServiceException)ex;
    if (dbEx.isSqlException()) {
      message=message + dbEx.getSqlCode() + " " + dbEx.getSqlState();
    }
  }
  message=message + ex.getMessage();
  return message;
}
