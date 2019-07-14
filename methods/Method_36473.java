@Override public HealthResult isHealthy(){
  HealthResult healthResult=new HealthResult(componentName.getRawName());
  healthResult.setHealthy(true);
  return healthResult;
}
