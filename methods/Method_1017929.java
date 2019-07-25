@Override public InspectionStatus inspect(Configuration configuration){
  LOG.debug("KyloDatabaseConnectionInspection.inspect");
  String url=configuration.getServicesProperty(SPRING_DATASOURCE_URL);
  String username=configuration.getServicesProperty(SPRING_DATASOURCE_USERNAME);
  String password=configuration.getServicesProperty(SPRING_DATASOURCE_PASSWORD);
  String driver=configuration.getServicesProperty(SPRING_DATASOURCE_DRIVER_CLASS_NAME);
  try {
    ClassLoader classloader=configuration.getServicesClassloader();
    Class<Driver> aClass=(Class<Driver>)classloader.loadClass(driver);
    Driver d=aClass.newInstance();
    DriverManager.registerDriver(new DriverShim(d));
  }
 catch (  InstantiationException|IllegalAccessException e) {
    InspectionStatus status=new InspectionStatus(false);
    status.addError(String.format("Failed to create driver '%s': %s",driver,e.getMessage()));
    return status;
  }
catch (  ClassNotFoundException e) {
    InspectionStatus status=new InspectionStatus(false);
    status.addError(String.format("Failed to load database driver '%s' from kylo-services classpath '%s'. " + "Ensure you have added driver jar to classpath and jar is readable by kylo-services user",driver,configuration.getServicesClasspath()));
    return status;
  }
catch (  SQLException e) {
    InspectionStatus status=new InspectionStatus(false);
    status.addError(String.format("Failed to register driver '%s' with DriverManager: %s",driver,e.getMessage()));
    return status;
  }
  try {
    Connection connection=DriverManager.getConnection(url,username,password);
    connection.close();
  }
 catch (  SQLException e) {
    InspectionStatus status=new InspectionStatus(false);
    status.addError(String.format("Failed to establish connection to database '%s' with driver '%s' and username '%s': %s. " + "Check if values are correct for following properties: '%s', '%s', '%s' in %s",url,driver,username,e.getMessage(),SPRING_DATASOURCE_URL,SPRING_DATASOURCE_USERNAME,SPRING_DATASOURCE_PASSWORD,configuration.getServicesConfigLocation()));
    return status;
  }
  return new InspectionStatus(true);
}
