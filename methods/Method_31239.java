protected final void recommendFlywayUpgradeIfNecessary(String newestSupportedVersion){
  if (getVersion().isNewerThan(newestSupportedVersion)) {
    LOG.warn("Flyway upgrade recommended: " + databaseType + " " + computeVersionDisplayName(getVersion()) + " is newer than this version of Flyway and support has not been tested.");
  }
}
