/** 
 * Extracts the schema version and the description from a migration name formatted as 1_2__Description.
 * @param migrationName The migration name to parse. Should not contain any folders or packages.
 * @param prefix        The migration prefix.
 * @param separator     The migration separator.
 * @param suffixes      The migration suffixes.
 * @param repeatable    Whether this is a repeatable migration.
 * @return The extracted schema version.
 * @throws FlywayException if the migration name does not follow the standard conventions.
 */
public static Pair<MigrationVersion,String> extractVersionAndDescription(String migrationName,String prefix,String separator,String[] suffixes,boolean repeatable){
  String cleanMigrationName=cleanMigrationName(migrationName,prefix,suffixes);
  int separatorPos=cleanMigrationName.indexOf(separator);
  String version;
  String description;
  if (separatorPos < 0) {
    version=cleanMigrationName;
    description="";
  }
 else {
    version=cleanMigrationName.substring(0,separatorPos);
    description=cleanMigrationName.substring(separatorPos + separator.length()).replace("_"," ");
  }
  if (StringUtils.hasText(version)) {
    if (repeatable) {
      throw new FlywayException("Wrong repeatable migration name format: " + migrationName + "(It cannot contain a version and should look like this: " + prefix + separator + description + suffixes[0] + ")");
    }
    return Pair.of(MigrationVersion.fromVersion(version),description);
  }
  if (!repeatable) {
    throw new FlywayException("Wrong versioned migration name format: " + migrationName + "(It must contain a version and should look like this: " + prefix + "1.2" + separator + description + suffixes[0] + ")");
  }
  return Pair.of(null,description);
}
