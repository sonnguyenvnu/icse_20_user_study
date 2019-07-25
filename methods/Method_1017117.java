@SuppressWarnings("unchecked") @GET @Path("image") @Produces("image/png") public Response render(@QueryParam("q") String queryString,@QueryParam("backend") String backendGroup,@QueryParam("title") String title,@QueryParam("width") Integer width,@QueryParam("height") Integer height,@QueryParam("highlight") String highlightRaw,@QueryParam("threshold") Double threshold) throws Exception {
  if (query == null) {
    throw new BadRequestException("'query' must be defined");
  }
  if (width == null) {
    width=DEFAULT_WIDTH;
  }
  if (height == null) {
    height=DEFAULT_HEIGHT;
  }
  final Map<String,String> highlight;
  if (highlightRaw != null) {
    highlight=mapper.readValue(highlightRaw,Map.class);
  }
 else {
    highlight=null;
  }
  final QueryContext queryContext=QueryContext.empty();
  final Query q=query.newQueryFromString(queryString).build();
  final QueryResult result=this.query.useGroup(backendGroup).query(q,queryContext).get();
  final JFreeChart chart=RenderUtils.createChart(result.getGroups(),title,highlight,threshold,height);
  final BufferedImage image=chart.createBufferedImage(width,height);
  final ByteArrayOutputStream buffer=new ByteArrayOutputStream();
  ImageIO.write(image,"png",buffer);
  return Response.ok(buffer.toByteArray()).build();
}
