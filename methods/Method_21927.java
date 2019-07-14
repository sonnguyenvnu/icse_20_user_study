/** 
 * ????????.
 * @param uriInfo ????
 * @return ?????????
 * @throws ParseException ????
 */
@GET @Path("/execution") @Produces(MediaType.APPLICATION_JSON) @Consumes(MediaType.APPLICATION_JSON) public JobEventRdbSearch.Result<JobExecutionEvent> findJobExecutionEvents(@Context final UriInfo uriInfo) throws ParseException {
  if (!eventTraceDataSourceConfigurationService.loadActivated().isPresent()) {
    return new JobEventRdbSearch.Result<>(0,new ArrayList<JobExecutionEvent>());
  }
  JobEventRdbSearch jobEventRdbSearch=new JobEventRdbSearch(setUpEventTraceDataSource());
  return jobEventRdbSearch.findJobExecutionEvents(buildCondition(uriInfo,new String[]{"jobName","ip","isSuccess"}));
}
