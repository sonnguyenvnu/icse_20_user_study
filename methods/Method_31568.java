private static String getDataSourceInfo(DataSource dataSource){
  if (!(dataSource instanceof DriverDataSource)) {
    return "";
  }
  DriverDataSource driverDataSource=(DriverDataSource)dataSource;
  return " (" + driverDataSource.getUrl() + ") for user '" + driverDataSource.getUser() + "'";
}
