@Override public InspectionStatus inspect(Configuration configuration){
  String servicesProperty=configuration.getServicesProperty(SERVER_PORT);
  String uiProperty=configuration.getUiProperty(ZUUL_ROUTES_API_URL);
  boolean valid=servicesProperty.equals(Integer.toString(URI.create(uiProperty).getPort()));
  InspectionStatus inspectionStatus=new InspectionStatus(valid);
  if (!valid) {
    inspectionStatus.addError("'server.port' property in kylo-services/conf/application.properties does not match port number specified by '" + ZUUL_ROUTES_API_URL + "' property in kylo-ui/conf/application.properties");
  }
  return inspectionStatus;
}
