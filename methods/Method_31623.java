/** 
 * Checks for incompatible migrations.
 * @param migrations The migrations to check.
 * @throws FlywayException when two different migration with the same version number are found.
 */
static void checkForIncompatibilities(List<ResolvedMigration> migrations){
  for (int i=0; i < migrations.size() - 1; i++) {
    ResolvedMigration current=migrations.get(i);
    ResolvedMigration next=migrations.get(i + 1);
    if (new ResolvedMigrationComparator().compare(current,next) == 0) {
      if (current.getVersion() != null) {
        throw new FlywayException(String.format("Found more than one migration with version %s\nOffenders:\n-> %s (%s)\n-> %s (%s)",current.getVersion(),current.getPhysicalLocation(),current.getType(),next.getPhysicalLocation(),next.getType()));
      }
      throw new FlywayException(String.format("Found more than one repeatable migration with description %s\nOffenders:\n-> %s (%s)\n-> %s (%s)",current.getDescription(),current.getPhysicalLocation(),current.getType(),next.getPhysicalLocation(),next.getType()));
    }
  }
}
