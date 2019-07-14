/** 
 * Create a MigrationVersion from a version String.
 * @param version The version String. The value {@code current} will be interpreted as MigrationVersion.CURRENT,a marker for the latest version that has been applied to the database.
 * @return The MigrationVersion
 */
@SuppressWarnings("ConstantConditions") public static MigrationVersion fromVersion(String version){
  if ("current".equalsIgnoreCase(version))   return CURRENT;
  if (LATEST.getVersion().equals(version))   return LATEST;
  if (version == null)   return EMPTY;
  return new MigrationVersion(version);
}
