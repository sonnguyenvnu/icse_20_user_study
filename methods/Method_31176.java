/** 
 * Ignore pending migrations when reading the schema history table. These are migrations that are available but have not yet been applied. This can be useful for verifying that in-development migration changes don't contain any validation-breaking changes of migrations that have already been applied to a production environment, e.g. as part of a CI/CD process, without failing because of the existence of new migration versions.
 * @param ignorePendingMigrations {@code true} to continue normally, {@code false} to fail fast with an exception.(default:  {@code false})
 */
public FluentConfiguration ignorePendingMigrations(boolean ignorePendingMigrations){
  config.setIgnorePendingMigrations(ignorePendingMigrations);
  return this;
}
