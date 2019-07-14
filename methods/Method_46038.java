@Override public void recover(MeasureResultDetail measureResultDetail){
  InvocationStatDimension statDimension=measureResultDetail.getInvocationStatDimension();
  ProviderInfo providerInfo=statDimension.getProviderInfo();
  if (providerInfo == null || providerInfo.getStatus() == ProviderStatus.WARMING_UP) {
    return;
  }
  Integer currentWeight=ProviderInfoWeightManager.getWeight(providerInfo);
  if (currentWeight == -1) {
    return;
  }
  String appName=statDimension.getAppName();
  double weightRecoverRate=FaultToleranceConfigManager.getWeightRecoverRate(appName);
  int recoverWeight=CalculateUtils.multiply(currentWeight,weightRecoverRate);
  int originWeight=statDimension.getOriginWeight();
  if (recoverWeight >= originWeight) {
    measureResultDetail.setRecoveredOriginWeight(true);
    ProviderInfoWeightManager.recoverOriginWeight(providerInfo,originWeight);
    if (LOGGER.isInfoEnabled(appName)) {
      LOGGER.infoWithApp(appName,"the weight was recovered to origin value. serviceUniqueName:[" + statDimension.getService() + "],ip:[" + statDimension.getIp() + "],origin weight:[" + currentWeight + "],recover weight:[" + originWeight + "].");
    }
  }
 else {
    measureResultDetail.setRecoveredOriginWeight(false);
    boolean success=ProviderInfoWeightManager.recoverWeight(providerInfo,recoverWeight);
    if (success && LOGGER.isInfoEnabled(appName)) {
      LOGGER.infoWithApp(appName,"the weight was recovered. serviceUniqueName:[" + statDimension.getService() + "],ip:[" + statDimension.getIp() + "],origin weight:[" + currentWeight + "],recover weight:[" + recoverWeight + "].");
    }
  }
}
