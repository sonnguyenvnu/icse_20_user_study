private void connect() throws IOException, SQLException {
  String url="jdbc:h2:~/test";
  String user="";
  String driver=null;
  try {
    Properties prop;
    if ("null".equals(serverPropertiesDir)) {
      prop=new Properties();
    }
 else {
      prop=SortedProperties.loadProperties(serverPropertiesDir + "/" + Constants.SERVER_PROPERTIES_NAME);
    }
    String data=null;
    boolean found=false;
    for (int i=0; ; i++) {
      String d=prop.getProperty(Integer.toString(i));
      if (d == null) {
        break;
      }
      found=true;
      data=d;
    }
    if (found) {
      ConnectionInfo info=new ConnectionInfo(data);
      url=info.url;
      user=info.user;
      driver=info.driver;
    }
  }
 catch (  IOException e) {
  }
  println("[Enter]   " + url);
  print("URL       ");
  url=readLine(url).trim();
  if (driver == null) {
    driver=JdbcUtils.getDriver(url);
  }
  if (driver != null) {
    println("[Enter]   " + driver);
  }
  print("Driver    ");
  driver=readLine(driver).trim();
  println("[Enter]   " + user);
  print("User      ");
  user=readLine(user);
  for (; ; ) {
    String password=readPassword();
    try {
      conn=JdbcUtils.getConnection(driver,url + ";IFEXISTS=TRUE",user,password);
      break;
    }
 catch (    SQLException ex) {
      if (ex.getErrorCode() == ErrorCode.DATABASE_NOT_FOUND_2) {
        println("Type the same password again to confirm database creation.");
        String password2=readPassword();
        if (password.equals(password2)) {
          conn=JdbcUtils.getConnection(driver,url,user,password);
          break;
        }
 else {
          println("Passwords don't match. Try again.");
          continue;
        }
      }
 else {
        throw ex;
      }
    }
  }
  stat=conn.createStatement();
  println("Connected");
}
