public static Connection getConn() throws Exception {
  String url=props.getProperty("spring.datasource.url");
  String username=props.getProperty("spring.datasource.username");
  String password=props.getProperty("spring.datasource.password");
  String driver=props.getProperty("spring.datasource.driver-class-name");
  Class.forName(driver);
  return DriverManager.getConnection(url,username,password);
}
