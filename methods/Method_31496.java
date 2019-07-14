@Override public final void ensureSupported(){
  ensureDatabaseIsRecentEnough("10.0");
  ensureDatabaseNotOlderThanOtherwiseRecommendUpgradeToFlywayEdition("12.0",org.flywaydb.core.internal.license.Edition.ENTERPRISE);
  ensureDatabaseNotOlderThanOtherwiseRecommendUpgradeToFlywayEdition("13.0",org.flywaydb.core.internal.license.Edition.PRO);
  recommendFlywayUpgradeIfNecessary("15.0");
}
