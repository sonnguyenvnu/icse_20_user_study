@Override public HealthResult isHealthy(){
  if (!isActivated()) {
    return super.isHealthy();
  }
  HealthResult healthResult=new HealthResult(componentName.getRawName());
  if (this.e == null) {
    healthResult.setHealthy(true);
  }
 else {
    healthResult.setHealthy(false);
    healthResult.setHealthReport(e.getMessage());
  }
  return healthResult;
}
