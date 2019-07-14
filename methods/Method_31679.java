/** 
 * Indicates in the schema history table that Flyway created these schemas.
 * @param schemas The schemas that were created by Flyway.
 */
public final void addSchemasMarker(Schema[] schemas){
  addAppliedMigration(null,"<< Flyway Schema Creation >>",MigrationType.SCHEMA,StringUtils.arrayToCommaDelimitedString(schemas),null,0,true);
}
