private String logout(){
  try {
    Connection conn=session.getConnection();
    session.setConnection(null);
    session.remove("conn");
    session.remove("result");
    session.remove("tables");
    session.remove("user");
    session.remove("tool");
    if (conn != null) {
      if (session.getShutdownServerOnDisconnect()) {
        server.shutdown();
      }
 else {
        conn.close();
      }
    }
  }
 catch (  Exception e) {
    trace(e.toString());
  }
  session.remove("admin");
  return "index.do";
}
