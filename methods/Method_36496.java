@Override public HealthResult healthCheck(){
  HealthResult healthResult=new HealthResult(getName());
  healthResult.setHealthy(isHealthy);
  return healthResult;
}
