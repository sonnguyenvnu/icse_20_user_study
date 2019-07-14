/** 
 * ????.
 * @param serverIp ???IP??
 */
@DELETE @Path("/{serverIp}/disable") public void enableServer(@PathParam("serverIp") final String serverIp){
  jobAPIService.getJobOperatorAPI().enable(Optional.<String>absent(),Optional.of(serverIp));
}
