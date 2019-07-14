@Override public final void ensureSupported(){
  ensureDatabaseIsRecentEnough("1.1");
  recommendFlywayUpgradeIfNecessary("2.1");
}
