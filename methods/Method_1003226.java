private String test(NetworkConnectionInfo networkConnectionInfo){
  String driver=attributes.getProperty("driver","");
  String url=attributes.getProperty("url","");
  String user=attributes.getProperty("user","");
  String password=attributes.getProperty("password","");
  session.put("driver",driver);
  session.put("url",url);
  session.put("user",user);
  boolean isH2=url.startsWith("jdbc:h2:");
  try {
    long start=System.currentTimeMillis();
    String profOpen="", profClose="";
    Profiler prof=new Profiler();
    prof.startCollecting();
    Connection conn;
    try {
      conn=server.getConnection(driver,url,user,password,null,networkConnectionInfo);
    }
  finally {
      prof.stopCollecting();
      profOpen=prof.getTop(3);
    }
    prof=new Profiler();
    prof.startCollecting();
    try {
      JdbcUtils.closeSilently(conn);
    }
  finally {
      prof.stopCollecting();
      profClose=prof.getTop(3);
    }
    long time=System.currentTimeMillis() - start;
    String success;
    if (time > 1000) {
      success="<a class=\"error\" href=\"#\" " + "onclick=\"var x=document.getElementById('prof').style;x." + "display=x.display==''?'none':'';\">" + "${text.login.testSuccessful}</a>" + "<span style=\"display: none;\" id=\"prof\"><br />" + PageParser.escapeHtml(profOpen) + "<br />" + PageParser.escapeHtml(profClose) + "</span>";
    }
 else {
      success="<div class=\"success\">${text.login.testSuccessful}</div>";
    }
    session.put("error",success);
    return "login.jsp";
  }
 catch (  Exception e) {
    session.put("error",getLoginError(e,isH2));
    return "login.jsp";
  }
}
