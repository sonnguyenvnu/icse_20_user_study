/** 
 * Convenience method for quickly checking whether this version is at least as new as this other version.
 * @param otherVersion The other version.
 * @return {@code true} if this version is equal or newer, {@code false} if it is older.
 */
public boolean isAtLeast(String otherVersion){
  return compareTo(MigrationVersion.fromVersion(otherVersion)) >= 0;
}
