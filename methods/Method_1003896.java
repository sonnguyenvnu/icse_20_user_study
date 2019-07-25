/** 
 * Services an incoming ping request with a default TTL.
 */
@GET @Path("/{message}") public String incoming(@PathParam("message") String message){
  return incoming(message,DEFAULT_TTL);
}
