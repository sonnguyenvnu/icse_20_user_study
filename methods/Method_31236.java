/** 
 * @return The major.minor version of the database.
 */
public final MigrationVersion getVersion(){
  if (version == null) {
    version=determineVersion();
  }
  return version;
}
