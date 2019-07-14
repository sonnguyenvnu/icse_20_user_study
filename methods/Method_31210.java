/** 
 * <p>Starts the database migration. All pending migrations will be applied in order. Calling migrate on an up-to-date database has no effect.</p> <img src="https://flywaydb.org/assets/balsamiq/command-migrate.png" alt="migrate">
 * @return The number of successfully applied migrations.
 * @throws FlywayException when the migration failed.
 */
public int migrate() throws FlywayException {
  return execute(new Command<Integer>(){
    public Integer execute(    MigrationResolver migrationResolver,    SchemaHistory schemaHistory,    Database database,    Schema[] schemas,    CallbackExecutor callbackExecutor){
      if (configuration.isValidateOnMigrate()) {
        doValidate(database,migrationResolver,schemaHistory,schemas,callbackExecutor,true);
      }
      if (!schemaHistory.exists()) {
        List<Schema> nonEmptySchemas=new ArrayList<>();
        for (        Schema schema : schemas) {
          if (schema.exists() && !schema.empty()) {
            nonEmptySchemas.add(schema);
          }
        }
        if (!nonEmptySchemas.isEmpty()) {
          if (configuration.isBaselineOnMigrate()) {
            doBaseline(schemaHistory,callbackExecutor);
          }
 else {
            if (!schemaHistory.exists()) {
              throw new FlywayException("Found non-empty schema(s) " + StringUtils.collectionToCommaDelimitedString(nonEmptySchemas) + " without schema history table! Use baseline()" + " or set baselineOnMigrate to true to initialize the schema history table.");
            }
          }
        }
 else {
          new DbSchemas(database,schemas,schemaHistory).create(false);
          schemaHistory.create(false);
        }
      }
      return new DbMigrate(database,schemaHistory,schemas[0],migrationResolver,configuration,callbackExecutor).migrate();
    }
  }
,true);
}
