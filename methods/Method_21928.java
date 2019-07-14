/** 
 * ????????.
 * @param uriInfo ????
 * @return ?????????
 * @throws ParseException ????
 */
@GET @Path("/status") @Produces(MediaType.APPLICATION_JSON) @Consumes(MediaType.APPLICATION_JSON) public JobEventRdbSearch.Result<JobStatusTraceEvent> findJobStatusTraceEvents(@Context final UriInfo uriInfo) throws ParseException {
  if (!eventTraceDataSourceConfigurationService.loadActivated().isPresent()) {
    return new JobEventRdbSearch.Result<>(0,new ArrayList<JobStatusTraceEvent>());
  }
  JobEventRdbSearch jobEventRdbSearch=new JobEventRdbSearch(setUpEventTraceDataSource());
  return jobEventRdbSearch.findJobStatusTraceEvents(buildCondition(uriInfo,new String[]{"jobName","source","executionType","state"}));
}
