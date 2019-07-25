private void rebalance(){
  if (null != sessionListSubscription) {
    final List<String> newSessions=sessionListSubscription.getData();
    final String sessionsHash=ZkSubscriptionClient.Topology.calculateSessionsHash(newSessions);
    zkClient.runLocked(() -> {
      final ZkSubscriptionClient.Topology topology=zkClient.getTopology();
      if (!topology.isSameHash(sessionsHash)) {
        log.info("Performing rebalance, hash changed: {}",sessionsHash);
        final Collection<Session> newSessionsUnderLock=zkClient.listSessions();
        final Partition[] changeset=rebalancer.apply(newSessionsUnderLock,topology.getPartitions());
        if (changeset.length > 0) {
          final String actualHash=ZkSubscriptionClient.Topology.calculateSessionsHash(newSessionsUnderLock.stream().map(Session::getId).collect(Collectors.toList()));
          zkClient.updatePartitionsConfiguration(actualHash,changeset);
        }
      }
 else {
        log.info("Skipping rebalance, because hash is the same: {}",sessionsHash);
      }
    }
);
  }
}
