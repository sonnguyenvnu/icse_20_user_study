private void upgrade(int version,int slotSize){
  for (  ForkBlockVersionEnum versionEnum : ForkBlockVersionEnum.values()) {
    int versionValue=versionEnum.getValue();
    if (versionValue < version) {
      byte[] stats=manager.getDynamicPropertiesStore().statsByVersion(versionValue);
      if (!check(stats)) {
        if (stats == null || stats.length == 0) {
          stats=new byte[slotSize];
        }
        Arrays.fill(stats,VERSION_UPGRADE);
        manager.getDynamicPropertiesStore().statsByVersion(versionValue,stats);
      }
    }
  }
}
