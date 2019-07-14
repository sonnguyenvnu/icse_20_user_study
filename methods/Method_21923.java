/** 
 * ???????????.
 * @param config ?????????
 * @param request HTTP????
 * @return ??????
 */
@POST @Path("/connectTest") @Produces(MediaType.APPLICATION_JSON) @Consumes(MediaType.APPLICATION_JSON) public boolean connectTest(final EventTraceDataSourceConfiguration config,final @Context HttpServletRequest request){
  return setDataSourceNameToSession(config,request.getSession());
}
