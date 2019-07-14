/** 
 * @param dbQueryInfo
 * @param project
 * @param metadata
 * @param job
 * @param limit
 * @param options
 * @param exceptions
 * @throws DatabaseServiceException
 */
private static void parsePreview(DatabaseQueryInfo dbQueryInfo,Project project,ProjectMetadata metadata,final ImportingJob job,int limit,ObjectNode options,List<Exception> exceptions) throws DatabaseServiceException {
  DatabaseService databaseService=DatabaseService.get(dbQueryInfo.getDbConfig().getDatabaseType());
  String querySource=getQuerySource(dbQueryInfo);
  List<DatabaseColumn> columns=databaseService.getColumns(dbQueryInfo.getDbConfig(),dbQueryInfo.getQuery());
  setProgress(job,querySource,-1);
  JSONUtilities.safePut(options,"ignoreLines",0);
  JSONUtilities.safePut(options,"headerLines",1);
  TabularImportingParserBase.readTable(project,metadata,job,new DBQueryResultPreviewReader(job,databaseService,querySource,columns,dbQueryInfo,100),querySource,limit,options,exceptions);
  setProgress(job,querySource,100);
}
