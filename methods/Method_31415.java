/** 
 * Checks whether Oracle Locator metadata exists for the schema.
 * @return {@code true} if it exists, {@code false} if not.
 * @throws SQLException when checking checking metadata existence failed.
 */
private boolean locatorMetadataExists() throws SQLException {
  return database.queryReturnsRows("SELECT * FROM ALL_SDO_GEOM_METADATA WHERE OWNER = ?",name);
}
