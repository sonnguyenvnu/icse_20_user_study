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
private static void parseCreate(DatabaseQueryInfo dbQueryInfo,Project project,ProjectMetadata metadata,final ImportingJob job,int limit,ObjectNode options,List<Exception> exceptions) throws DatabaseServiceException {
  DatabaseService databaseService=DatabaseService.get(dbQueryInfo.getDbConfig().getDatabaseType());
  String querySource=getQuerySource(dbQueryInfo);
  List<DatabaseColumn> columns=databaseService.getColumns(dbQueryInfo.getDbConfig(),dbQueryInfo.getQuery());
  setProgress(job,querySource,-1);
  JSONUtilities.safePut(options,"ignoreLines",0);
  JSONUtilities.safePut(options,"headerLines",1);
  long startTime=System.currentTimeMillis();
  TabularImportingParserBase.readTable(project,metadata,job,new DBQueryResultImportReader(job,databaseService,querySource,columns,dbQueryInfo,getCreateBatchSize()),querySource,limit,options,exceptions);
  long endTime=System.currentTimeMillis();
  if (logger.isDebugEnabled()) {
    logger.debug("Execution Time: {}",endTime - startTime);
  }
  setProgress(job,querySource,100);
}
