@Override public EndpointStatus join(InetSocketAddress endpoint,Map<String,InetSocketAddress> additionalEndpoints,Status status) throws JoinException, InterruptedException {
  LOG.warning("This method is deprecated. Please do not specify a status field.");
  if (status != Status.ALIVE) {
    LOG.severe("**************************************************************************\n" + "WARNING: MUTABLE STATUS FIELDS ARE NO LONGER SUPPORTED.\n" + "JOINING WITH STATUS ALIVE EVEN THOUGH YOU SPECIFIED " + status + "\n**************************************************************************");
  }
  return join(endpoint,additionalEndpoints);
}
