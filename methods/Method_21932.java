/** 
 * ??????.
 * @return ??????
 */
@GET @Produces(MediaType.APPLICATION_JSON) public Collection<JobBriefInfo> getAllJobsBriefInfo(){
  return jobAPIService.getJobStatisticsAPI().getAllJobsBriefInfo();
}
