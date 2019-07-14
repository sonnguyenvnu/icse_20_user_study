/** 
 * Ignore missing migrations when reading the schema history table. These are migrations that were performed by an older deployment of the application that are no longer available in this version. For example: we have migrations available on the classpath with versions 1.0 and 3.0. The schema history table indicates that a migration with version 2.0 (unknown to us) has also been applied. Instead of bombing out (fail fast) with an exception, a warning is logged and Flyway continues normally. This is useful for situations where one must be able to deploy a newer version of the application even though it doesn't contain migrations included with an older one anymore. Note that if the most recently applied migration is removed, Flyway has no way to know it is missing and will mark it as future instead.
 * @param ignoreMissingMigrations {@code true} to continue normally and log a warning, {@code false} to fail fast with an exception.(default:  {@code false})
 */
public FluentConfiguration ignoreMissingMigrations(boolean ignoreMissingMigrations){
  config.setIgnoreMissingMigrations(ignoreMissingMigrations);
  return this;
}
