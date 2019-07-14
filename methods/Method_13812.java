/** 
 * @param response
 * @param savedConnection
 * @throws IOException
 * @throws JSONException
 */
private void writeSavedConnectionResponse(HttpServletResponse response,DatabaseConfiguration savedConnection) throws IOException {
  Writer w=response.getWriter();
  try {
    JsonGenerator writer=ParsingUtilities.mapper.getFactory().createGenerator(w);
    writer.writeStartObject();
    writer.writeArrayFieldStart(DatabaseUtils.SAVED_CONNECTION_KEY);
    writer.writeStartObject();
    writer.writeStringField("connectionName",savedConnection.getConnectionName());
    writer.writeStringField("databaseType",savedConnection.getDatabaseType());
    writer.writeStringField("databaseHost",savedConnection.getDatabaseHost());
    writer.writeNumberField("databasePort",savedConnection.getDatabasePort());
    writer.writeStringField("databaseName",savedConnection.getDatabaseName());
    String dbPasswd=savedConnection.getDatabasePassword();
    if (dbPasswd != null && !dbPasswd.isEmpty()) {
      dbPasswd=DatabaseUtils.decrypt(savedConnection.getDatabasePassword());
    }
    writer.writeStringField("databasePassword",dbPasswd);
    writer.writeStringField("databaseSchema",savedConnection.getDatabaseSchema());
    writer.writeStringField("databaseUser",savedConnection.getDatabaseUser());
    writer.writeEndObject();
    writer.writeEndArray();
    writer.writeEndObject();
    writer.flush();
    writer.close();
  }
  finally {
    w.flush();
    w.close();
  }
}
