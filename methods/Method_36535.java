@Override public HealthResult isHealthy(){
  HealthResult healthResult=new HealthResult(componentName.getRawName());
  if (!isActivated()) {
    healthResult.setHealthy(false);
    healthResult.setHealthReport("Status: " + this.getState().toString());
  }
 else {
    healthResult.setHealthy(true);
  }
  return healthResult;
}
