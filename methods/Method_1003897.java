/** 
 * Services an incoming ping request.
 */
@GET @Path("/{message}/{ttl}") public String incoming(@PathParam("message") final String message,@PathParam("ttl") int ttl){
  LOG.info("Got ping, ttl=" + ttl);
  PINGS.incrementAndGet();
  if (ttl > 1) {
    client.execute("/ping/" + message + "/" + (ttl - 1));
  }
  return "pong\n";
}
