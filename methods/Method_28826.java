public Response<String> clusterAddSlots(final int... slots){
  client.clusterAddSlots(slots);
  return getResponse(BuilderFactory.STRING);
}
