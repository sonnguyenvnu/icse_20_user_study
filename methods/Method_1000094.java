public void finish(){
  dbManager.getDynamicPropertiesStore().saveTokenUpdateDone(1);
  assetNameToIdMap.clear();
}
