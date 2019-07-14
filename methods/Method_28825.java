public Response<String> clusterMeet(final String ip,final int port){
  client.clusterMeet(ip,port);
  return getResponse(BuilderFactory.STRING);
}
