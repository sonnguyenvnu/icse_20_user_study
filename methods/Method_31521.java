@Override public void ensureSupported(){
  ensureDatabaseIsRecentEnough("15.7");
  ensureDatabaseNotOlderThanOtherwiseRecommendUpgradeToFlywayEdition("16.0",org.flywaydb.core.internal.license.Edition.ENTERPRISE);
  recommendFlywayUpgradeIfNecessary("16.2");
}
