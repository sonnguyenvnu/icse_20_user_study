private String login(NetworkConnectionInfo networkConnectionInfo){
  String driver=attributes.getProperty("driver","");
  String url=attributes.getProperty("url","");
  String user=attributes.getProperty("user","");
  String password=attributes.getProperty("password","");
  session.put("autoCommit","checked");
  session.put("autoComplete","1");
  session.put("maxrows","1000");
  boolean isH2=url.startsWith("jdbc:h2:");
  try {
    Connection conn=server.getConnection(driver,url,user,password,(String)session.get("key"),networkConnectionInfo);
    session.setConnection(conn);
    session.put("url",url);
    session.put("user",user);
    session.remove("error");
    settingSave();
    return "frame.jsp";
  }
 catch (  Exception e) {
    session.put("error",getLoginError(e,isH2));
    return "login.jsp";
  }
}
