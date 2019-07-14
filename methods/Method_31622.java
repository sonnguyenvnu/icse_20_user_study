/** 
 * Finds all available migrations using all migration resolvers (sql, java, ...).
 * @return The available migrations, sorted by version, oldest first. An empty list is returned when no migrationscan be found.
 * @throws FlywayException when the available migrations have overlapping versions.
 */
private List<ResolvedMigration> doFindAvailableMigrations(Context context) throws FlywayException {
  List<ResolvedMigration> migrations=new ArrayList<>(collectMigrations(migrationResolvers,context));
  Collections.sort(migrations,new ResolvedMigrationComparator());
  checkForIncompatibilities(migrations);
  return migrations;
}
