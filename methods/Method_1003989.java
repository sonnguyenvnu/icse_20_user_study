/** 
 * Attempts to lead the singleton service.
 * @param endpoint The primary endpoint to register as a leader candidate in the service.
 * @param additionalEndpoints Additional endpoints that are available on the host.
 * @param status deprecated, will be ignored entirely
 * @param listener Handler to call when the candidate is elected or defeated.
 * @throws Group.WatchException If there was a problem watching the ZooKeeper group.
 * @throws Group.JoinException If there was a problem joining the ZooKeeper group.
 * @throws InterruptedException If the thread watching/joining the group was interrupted.
 * @deprecated The status field is deprecated. Please use{@link #lead(InetSocketAddress,Map,LeadershipListener)}
 */
@Deprecated public void lead(final InetSocketAddress endpoint,final Map<String,InetSocketAddress> additionalEndpoints,final Status status,final LeadershipListener listener) throws Group.WatchException, Group.JoinException, InterruptedException {
  if (status != Status.ALIVE) {
    LOG.severe("******************************************************************************");
    LOG.severe("WARNING: MUTABLE STATUS FIELDS ARE NO LONGER SUPPORTED.");
    LOG.severe("JOINING WITH STATUS ALIVE EVEN THOUGH YOU SPECIFIED " + status);
    LOG.severe("******************************************************************************");
  }
 else {
    LOG.warning("******************************************************************************");
    LOG.warning("WARNING: MUTABLE STATUS FIELDS ARE NO LONGER SUPPORTED.");
    LOG.warning("Please use SingletonService.lead(InetSocketAddress, Map, LeadershipListener)");
    LOG.warning("******************************************************************************");
  }
  lead(endpoint,additionalEndpoints,listener);
}
