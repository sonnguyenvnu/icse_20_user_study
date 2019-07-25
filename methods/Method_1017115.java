@POST @Path("metrics") @Consumes(MediaType.APPLICATION_JSON) public void metrics(@Suspended final AsyncResponse response,@QueryParam("group") String group,@Context final HttpServletRequest servletReq,final QueryMetrics query){
  final HttpContext httpContext=CoreHttpContextFactory.create(servletReq);
  final QueryContext queryContext=QueryContext.create(query.clientContext(),httpContext);
  queryLogger.logHttpQueryJson(queryContext,query);
  final Query q=query.toQueryBuilder(this.query::newQueryFromString).build();
  final QueryManager.Group g=this.query.useOptionalGroup(Optional.ofNullable(group));
  final AsyncFuture<QueryResult> callback=g.query(q,queryContext);
  bindMetricsResponse(response,callback,queryContext);
}
