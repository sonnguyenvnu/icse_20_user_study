public void shutdown(){
  if (connection != null) {
    try {
      connection.close();
    }
 catch (    SQLException e) {
      logger.warn("Non-Managed connection could not be closed. Whoops!",e);
    }
  }
}
