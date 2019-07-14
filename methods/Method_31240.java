protected final void recommendFlywayUpgradeIfNecessaryForMajorVersion(String newestSupportedVersion){
  if (getVersion().isMajorNewerThan(newestSupportedVersion)) {
    LOG.warn("Flyway upgrade recommended: " + databaseType + " " + computeVersionDisplayName(getVersion()) + " is newer than this version of Flyway and support has not been tested.");
  }
}
