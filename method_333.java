@POST @Path("/login") @Produces(MediaType.APPLICATION_JSON) public Response _XXXXX_(@Auth User user){
  return RESTResponse.of(user).message("Login successfully as " + user.getName()).status(true,Response.Status.OK).build();
}