public boolean isAutoDeregistration(Environment environment){
  return this.autoDeregistration != null ? this.autoDeregistration : CloudPlatform.getActive(environment) != null;
}
