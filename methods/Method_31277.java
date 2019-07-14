@Override public final void ensureSupported(){
  ensureDatabaseIsRecentEnough("9.7");
  ensureDatabaseNotOlderThanOtherwiseRecommendUpgradeToFlywayEdition("11.1",org.flywaydb.core.internal.license.Edition.ENTERPRISE);
  recommendFlywayUpgradeIfNecessary("11.1");
}
