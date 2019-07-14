public Response<String> clusterSetSlotNode(final int slot,final String nodeId){
  client.clusterSetSlotNode(slot,nodeId);
  return getResponse(BuilderFactory.STRING);
}
