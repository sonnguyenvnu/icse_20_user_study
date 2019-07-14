@Override public void batchSave(List<AppClientCostTimeStat> appClientCostTimeStatList){
  if (CollectionUtils.isEmpty(appClientCostTimeStatList)) {
    return;
  }
  try {
    List<AppInstanceClientRelation> appInstanceClientRelationList=new ArrayList<AppInstanceClientRelation>();
    for (    AppClientCostTimeStat appClientCostTimeStat : appClientCostTimeStatList) {
      AppInstanceClientRelation appInstanceClientRelation=AppInstanceClientRelation.generateFromAppClientCostTimeStat(appClientCostTimeStat);
      if (appInstanceClientRelation != null) {
        appInstanceClientRelationList.add(appInstanceClientRelation);
      }
    }
    if (CollectionUtils.isNotEmpty(appInstanceClientRelationList)) {
      appInstanceClientRelationDao.batchSave(appInstanceClientRelationList);
    }
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
}
