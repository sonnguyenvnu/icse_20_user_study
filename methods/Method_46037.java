@Override public void degrade(MeasureResultDetail measureResultDetail){
  super.degrade(measureResultDetail);
  if (measureResultDetail.isLogOnly()) {
    return;
  }
  InvocationStatDimension statDimension=measureResultDetail.getInvocationStatDimension();
  String appName=statDimension.getAppName();
  ProviderInfo providerInfo=statDimension.getProviderInfo();
  if (providerInfo == null || providerInfo.getStatus() == ProviderStatus.WARMING_UP) {
    return;
  }
  int currentWeight=ProviderInfoWeightManager.getWeight(providerInfo);
  double weightDegradeRate=FaultToleranceConfigManager.getWeightDegradeRate(appName);
  int degradeLeastWeight=FaultToleranceConfigManager.getDegradeLeastWeight(appName);
  int degradeWeight=CalculateUtils.multiply(currentWeight,weightDegradeRate);
  degradeWeight=degradeWeight < degradeLeastWeight ? degradeLeastWeight : degradeWeight;
  boolean success=ProviderInfoWeightManager.degradeWeight(providerInfo,degradeWeight);
  if (success && LOGGER.isInfoEnabled(appName)) {
    LOGGER.infoWithApp(appName,"the weight was degraded. serviceUniqueName:[" + statDimension.getService() + "],ip:[" + statDimension.getIp() + "],origin weight:[" + currentWeight + "],degraded weight:[" + degradeWeight + "].");
  }
}
