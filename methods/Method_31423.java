@Override public final void ensureSupported(){
  ensureDatabaseIsRecentEnough("9.0");
  ensureDatabaseNotOlderThanOtherwiseRecommendUpgradeToFlywayEdition("9.4",org.flywaydb.core.internal.license.Edition.ENTERPRISE);
  ensureDatabaseNotOlderThanOtherwiseRecommendUpgradeToFlywayEdition("9.5",org.flywaydb.core.internal.license.Edition.PRO);
  recommendFlywayUpgradeIfNecessaryForMajorVersion("12");
}
