@Override public void changeCurrentSchemaTo(Schema schema){
  try {
    if (schema.getName().equals(originalSchemaNameOrSearchPath) || originalSchemaNameOrSearchPath.startsWith(schema.getName() + ",") || !schema.exists()) {
      return;
    }
    if (StringUtils.hasText(originalSchemaNameOrSearchPath)) {
      doChangeCurrentSchemaOrSearchPathTo(schema.toString() + "," + originalSchemaNameOrSearchPath);
    }
 else {
      doChangeCurrentSchemaOrSearchPathTo(schema.toString());
    }
  }
 catch (  SQLException e) {
    throw new FlywaySqlException("Error setting current schema to " + schema,e);
  }
}
