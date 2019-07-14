/** 
 * ???.
 */
public void init(){
  log.debug("Elastic job: data source init, connection url is: {}.",eventTraceDataSourceConfiguration.getUrl());
  try {
    Class.forName(eventTraceDataSourceConfiguration.getDriver());
    DriverManager.getConnection(eventTraceDataSourceConfiguration.getUrl(),eventTraceDataSourceConfiguration.getUsername(),eventTraceDataSourceConfiguration.getPassword());
  }
 catch (  final ClassNotFoundException|SQLException ex) {
    throw new RuntimeException(ex);
  }
}
