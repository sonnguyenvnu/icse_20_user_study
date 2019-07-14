/** 
 * ???????.
 * @return ???????
 */
@GET @Produces(MediaType.APPLICATION_JSON) public Collection<ServerBriefInfo> getAllServersBriefInfo(){
  return jobAPIService.getServerStatisticsAPI().getAllServersBriefInfo();
}
