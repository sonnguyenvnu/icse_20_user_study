public synchronized void reset(){
  for (  ForkBlockVersionEnum versionEnum : ForkBlockVersionEnum.values()) {
    int versionValue=versionEnum.getValue();
    byte[] stats=manager.getDynamicPropertiesStore().statsByVersion(versionValue);
    if (!check(stats) && Objects.nonNull(stats)) {
      Arrays.fill(stats,VERSION_DOWNGRADE);
      manager.getDynamicPropertiesStore().statsByVersion(versionValue,stats);
    }
  }
}
