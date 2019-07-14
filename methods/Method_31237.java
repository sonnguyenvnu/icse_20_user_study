protected final void ensureDatabaseIsRecentEnough(String oldestSupportedVersion){
  if (!getVersion().isAtLeast(oldestSupportedVersion)) {
    throw new FlywayDbUpgradeRequiredException(databaseType,computeVersionDisplayName(getVersion()),computeVersionDisplayName(MigrationVersion.fromVersion(oldestSupportedVersion)));
  }
}
