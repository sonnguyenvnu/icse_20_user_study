/** 
 * Checks whether the schema history table contains a marker row for schema creation.
 * @return {@code true} if it does, {@code false} if it doesn't.
 */
public final boolean hasSchemasMarker(){
  List<AppliedMigration> appliedMigrations=allAppliedMigrations();
  return !appliedMigrations.isEmpty() && appliedMigrations.get(0).getType() == MigrationType.SCHEMA;
}
