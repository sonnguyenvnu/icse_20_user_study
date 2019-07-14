/** 
 * Performs the actual validation. All set up must have taken place beforehand.
 * @param database          The database-specific support.
 * @param migrationResolver The migration resolver;
 * @param schemaHistory     The schema history table.
 * @param schemas           The schemas managed by Flyway.
 * @param callbackExecutor  The callback executor.
 * @param ignorePending     Whether to ignore pending migrations.
 */
private void doValidate(Database database,MigrationResolver migrationResolver,SchemaHistory schemaHistory,Schema[] schemas,CallbackExecutor callbackExecutor,boolean ignorePending){
  String validationError=new DbValidate(database,schemaHistory,schemas[0],migrationResolver,configuration,ignorePending,callbackExecutor).validate();
  if (validationError != null) {
    if (configuration.isCleanOnValidationError()) {
      doClean(database,schemaHistory,schemas,callbackExecutor);
    }
 else {
      throw new FlywayException("Validate failed: " + validationError);
    }
  }
}
