@GET @Produces(MediaType.APPLICATION_JSON) @Consumes(MediaType.WILDCARD) public Response ping(){
  Map<String,String> pong=new HashMap<>();
  pong.put("pong","Hello, World!");
  return Response.status(200).entity(pong).build();
}
