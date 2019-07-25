public synchronized boolean pass(int version){
  if (version == ForkBlockVersionConsts.ENERGY_LIMIT) {
    return checkForEnergyLimit();
  }
  byte[] stats=manager.getDynamicPropertiesStore().statsByVersion(version);
  return check(stats);
}
