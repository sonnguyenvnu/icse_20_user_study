@Override @Path("path-variables/{p1}/{p2}") @GET public String pathVariables(@PathParam("p1") String path1,@PathParam("p2") String path2,@QueryParam("v") String param){
  String result=path1 + " , " + path2 + " , " + param;
  log("/path-variables",result);
  return result;
}
