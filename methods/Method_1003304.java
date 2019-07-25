private static void process(String urlSource,String urlTarget,String user,String password,String serverList) throws SQLException {
  org.h2.Driver.load();
  try (Connection connSource=DriverManager.getConnection(urlSource + ";CLUSTER=''",user,password);Statement statSource=connSource.createStatement()){
    statSource.execute("SET EXCLUSIVE 2");
    try {
      performTransfer(statSource,urlTarget,user,password,serverList);
    }
  finally {
      statSource.execute("SET EXCLUSIVE FALSE");
    }
  }
 }
