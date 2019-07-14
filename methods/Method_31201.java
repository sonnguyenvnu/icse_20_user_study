/** 
 * Convenience method for quickly checking whether this major version is newer than this other major version.
 * @param otherVersion The other version.
 * @return {@code true} if this major version is newer, {@code false} if it is not.
 */
public boolean isMajorNewerThan(String otherVersion){
  return getMajor().compareTo(MigrationVersion.fromVersion(otherVersion).getMajor()) > 0;
}
