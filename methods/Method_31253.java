@Override public void changeCurrentSchemaTo(Schema schema){
  try {
    if (schema.getName().equals(originalSchemaNameOrSearchPath) || !schema.exists()) {
      return;
    }
    doChangeCurrentSchemaOrSearchPathTo(schema.getName());
  }
 catch (  SQLException e) {
    throw new FlywaySqlException("Error setting current database to " + schema,e);
  }
}
