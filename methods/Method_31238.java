/** 
 * Ensures this database it at least at recent as this version otherwise suggest upgrade to this higher edition of Flyway.
 * @param oldestSupportedVersionInThisEdition The oldest supported version of the database by this edition of Flyway.
 * @param editionWhereStillSupported          The edition of Flyway that still supports this version of the database,in case it's too old.
 */
protected final void ensureDatabaseNotOlderThanOtherwiseRecommendUpgradeToFlywayEdition(String oldestSupportedVersionInThisEdition,Edition editionWhereStillSupported){
  if (!getVersion().isAtLeast(oldestSupportedVersionInThisEdition)) {
    throw new FlywayEditionUpgradeRequiredException(editionWhereStillSupported,databaseType,computeVersionDisplayName(getVersion()));
  }
}
