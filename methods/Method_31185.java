/** 
 * <p> Whether to automatically call baseline when migrate is executed against a non-empty schema with no schema history table. This schema will then be baselined with the  {@code baselineVersion} before executing the migrations.Only migrations above  {@code baselineVersion} will then be applied.</p> <p> This is useful for initial Flyway production deployments on projects with an existing DB. </p> <p> Be careful when enabling this as it removes the safety net that ensures Flyway does not migrate the wrong database in case of a configuration mistake! </p>
 * @param baselineOnMigrate {@code true} if baseline should be called on migrate for non-empty schemas, {@code false} if not. (default: {@code false})
 */
public FluentConfiguration baselineOnMigrate(boolean baselineOnMigrate){
  config.setBaselineOnMigrate(baselineOnMigrate);
  return this;
}
