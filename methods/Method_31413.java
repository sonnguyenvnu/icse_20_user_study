@Override protected void doClean() throws SQLException {
  if (isSystem()) {
    throw new FlywayException("Clean not supported on Oracle for system schema " + database.quote(name) + "! " + "It must not be changed in any way except by running an Oracle-supplied script!");
  }
  if (database.isFlashbackDataArchiveAvailable()) {
    disableFlashbackArchiveForFbaTrackedTables();
  }
  if (database.isLocatorAvailable()) {
    cleanLocatorMetadata();
  }
  Set<String> objectTypeNames=getObjectTypeNames(jdbcTemplate,database,this);
  List<ObjectType> objectTypesToClean=Arrays.asList(TRIGGER,QUEUE_TABLE,FILE_WATCHER,SCHEDULER_CHAIN,SCHEDULER_JOB,SCHEDULER_PROGRAM,SCHEDULE,RULE_SET,RULE,EVALUATION_CONTEXT,FILE_GROUP,XML_SCHEMA,MINING_MODEL,REWRITE_EQUIVALENCE,SQL_TRANSLATION_PROFILE,MATERIALIZED_VIEW,MATERIALIZED_VIEW_LOG,DIMENSION,VIEW,DOMAIN_INDEX,DOMAIN_INDEX_TYPE,TABLE,INDEX,CLUSTER,SEQUENCE,OPERATOR,FUNCTION,PROCEDURE,PACKAGE,CONTEXT,LIBRARY,TYPE,SYNONYM,JAVA_SOURCE,JAVA_CLASS,JAVA_RESOURCE,DATABASE_LINK,CREDENTIAL,DATABASE_DESTINATION,SCHEDULER_GROUP,CUBE,CUBE_DIMENSION,CUBE_BUILD_PROCESS,MEASURE_FOLDER,ASSEMBLY,JAVA_DATA);
  for (  ObjectType objectType : objectTypesToClean) {
    if (objectTypeNames.contains(objectType.getName())) {
      LOG.debug("Cleaning objects of type " + objectType + " ...");
      objectType.dropObjects(jdbcTemplate,database,this);
    }
  }
  if (isDefaultSchemaForUser()) {
    jdbcTemplate.execute("PURGE RECYCLEBIN");
  }
}
