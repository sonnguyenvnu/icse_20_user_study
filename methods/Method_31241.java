/** 
 * Compute the user-friendly display name for this database version.
 * @return The user-friendly display name.
 */
protected String computeVersionDisplayName(MigrationVersion version){
  return version.getVersion();
}
