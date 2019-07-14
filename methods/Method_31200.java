/** 
 * Convenience method for quickly checking whether this version is newer than this other version.
 * @param otherVersion The other version.
 * @return {@code true} if this version is newer, {@code false} if it is not.
 */
public boolean isNewerThan(String otherVersion){
  return compareTo(MigrationVersion.fromVersion(otherVersion)) > 0;
}
