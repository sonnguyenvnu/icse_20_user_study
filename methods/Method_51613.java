private List<SourceObject> findSourceObjectFromMetaData(DatabaseMetaData metadata,String schema,String sourceCodeName) throws SQLException {
  List<SourceObject> sourceObjectsList=new ArrayList<>();
  try (ResultSet sourceCodeObjects=metadata.getProcedures(null,schema,sourceCodeName)){
    while (sourceCodeObjects.next()) {
      LOGGER.finest(String.format("Located schema=%s,object_type=%s,object_name=%s\n",sourceCodeObjects.getString("PROCEDURE_SCHEM"),sourceCodeObjects.getString("PROCEDURE_TYPE"),sourceCodeObjects.getString("PROCEDURE_NAME")));
      sourceObjectsList.add(new SourceObject(sourceCodeObjects.getString("PROCEDURE_SCHEM"),sourceCodeObjects.getString("PROCEDURE_TYPE"),sourceCodeObjects.getString("PROCEDURE_NAME"),null));
    }
  }
   return sourceObjectsList;
}
