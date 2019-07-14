@Override public void ensureSupported(){
  ensureDatabaseNotOlderThanOtherwiseRecommendUpgradeToFlywayEdition("2",org.flywaydb.core.internal.license.Edition.ENTERPRISE);
  recommendFlywayUpgradeIfNecessaryForMajorVersion("2");
}
