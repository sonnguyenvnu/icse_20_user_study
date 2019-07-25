private void downgrade(int version,int slot){
  for (  ForkBlockVersionEnum versionEnum : ForkBlockVersionEnum.values()) {
    int versionValue=versionEnum.getValue();
    if (versionValue > version) {
      byte[] stats=manager.getDynamicPropertiesStore().statsByVersion(versionValue);
      if (!check(stats) && Objects.nonNull(stats)) {
        stats[slot]=VERSION_DOWNGRADE;
        manager.getDynamicPropertiesStore().statsByVersion(versionValue,stats);
      }
    }
  }
}
