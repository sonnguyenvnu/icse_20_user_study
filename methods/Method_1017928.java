@Override public InspectionStatus inspect(Configuration configuration){
  String servicesJwtKey=configuration.getServicesProperty(SECURITY_JWT_KEY);
  String uiJwtKey=configuration.getUiProperty(SECURITY_JWT_KEY);
  boolean valid=servicesJwtKey.equals(uiJwtKey);
  InspectionStatus inspectionStatus=new InspectionStatus(valid);
  if (!valid) {
    inspectionStatus.addError("'security.jwt.key' property in kylo-services/conf/application.properties does not match 'security.jwt.key' property in kylo-ui/conf/application.properties");
  }
  return inspectionStatus;
}
