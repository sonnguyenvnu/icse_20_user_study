/** 
 * Executes a query on the specified datasource.
 * @param idStr the datasource id
 * @param query the SQL query
 * @return the SQL result
 */
@GET @Path("{id}/query") @Produces(MediaType.APPLICATION_JSON) @ApiOperation("Executes a query and returns the result.") @ApiResponses({@ApiResponse(code=200,message="Returns the result.",response=QueryResult.class),@ApiResponse(code=403,message="Access denied.",response=RestResponseStatus.class),@ApiResponse(code=400,message="A JDBC data source with that id does not exist.",response=RestResponseStatus.class),@ApiResponse(code=500,message="NiFi or the database are unavailable.",response=RestResponseStatus.class)}) public Response query(@PathParam("id") final String idStr,@QueryParam("query") final String query){
  final Optional<com.thinkbiganalytics.metadata.api.datasource.Datasource.ID> id=metadata.read(() -> {
    accessController.checkPermission(AccessController.SERVICES,FeedServicesAccessControl.ACCESS_DATASOURCES);
    final com.thinkbiganalytics.metadata.api.datasource.Datasource datasource=datasourceProvider.getDatasource(datasourceProvider.resolve(idStr));
    return Optional.ofNullable(datasource).map(com.thinkbiganalytics.metadata.api.datasource.Datasource::getId);
  }
);
  return metadata.read(() -> {
    final QueryResult result=id.map(datasourceProvider::getDatasource).map(ds -> datasourceTransform.toDatasource(ds,DatasourceModelTransform.Level.ADMIN)).filter(JdbcDatasource.class::isInstance).map(JdbcDatasource.class::cast).map(datasource -> dbcpConnectionPoolTableInfo.executeQueryForDatasource(datasource,query)).orElseThrow(() -> new NotFoundException("No JDBC datasource exists with the given ID: " + idStr));
    return Response.ok(result).build();
  }
,MetadataAccess.SERVICE);
}
