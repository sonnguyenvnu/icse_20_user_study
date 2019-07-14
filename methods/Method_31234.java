/** 
 * Sets the current schema to this schema.
 * @param schema The new current schema for this connection.
 */
public void changeCurrentSchemaTo(Schema schema){
  try {
    if (!schema.exists()) {
      return;
    }
    doChangeCurrentSchemaOrSearchPathTo(schema.getName());
  }
 catch (  SQLException e) {
    throw new FlywaySqlException("Error setting current schema to " + schema,e);
  }
}
