/** 
 * Finds all available migrations using all migration resolvers (sql, java, ...).
 * @return The available migrations, sorted by version, oldest first. An empty list is returned when no migrationscan be found.
 * @throws FlywayException when the available migrations have overlapping versions.
 */
public List<ResolvedMigration> resolveMigrations(Context context){
  if (availableMigrations == null) {
    availableMigrations=doFindAvailableMigrations(context);
  }
  return availableMigrations;
}
