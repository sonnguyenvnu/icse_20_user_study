/** 
 * Executes a SQL query.
 * @param request the query request
 * @return the query status
 */
@POST @Path("/query") @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON) @ApiOperation("Queries a data source table.") @ApiResponses({@ApiResponse(code=200,message="Returns the status of the query.",response=TransformResponse.class),@ApiResponse(code=400,message="The requested data source does not exist.",response=RestResponseStatus.class),@ApiResponse(code=500,message="There was a problem processing the data.",response=RestResponseStatus.class)}) @Nonnull public Response query(@ApiParam(value="The request indicates the query to execute. Exactly one source must be specified.",required=true) @Nullable final TransformRequest request){
  if (request == null || request.getScript() == null) {
    throw transformError(Response.Status.BAD_REQUEST,"query.missingScript",null);
  }
  addDatasourceDetails(request);
  addCatalogDataSets(request);
  final SparkShellProcess process=getSparkShellProcess();
  return getTransformResponse(() -> restClient.query(process,request));
}
