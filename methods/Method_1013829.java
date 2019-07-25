private void remove(ProxyEndpoint endpoint) throws Exception {
  for (  PingStats pingStats : allPingStats) {
    if (pingStats.getEndpoint().equals(endpoint)) {
      pingStats.stop();
      allPingStats.remove(pingStats);
    }
  }
}
