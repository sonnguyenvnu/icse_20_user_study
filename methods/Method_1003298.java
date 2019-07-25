private void connect(){
  connectException=null;
  for (int retry=0; ; retry++) {
    try {
      conn=database.getLinkConnection(driver,url,user,password);
synchronized (conn) {
        try {
          readMetaData();
          return;
        }
 catch (        Exception e) {
          conn.close(true);
          conn=null;
          throw DbException.convert(e);
        }
      }
    }
 catch (    DbException e) {
      if (retry >= MAX_RETRY) {
        connectException=e;
        throw e;
      }
    }
  }
}
