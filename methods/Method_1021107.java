@Override public void transfer(final String sessionId,final Collection<EventTypePartition> partitions) throws NakadiRuntimeException, SubscriptionNotInitializedException {
  getLog().info("session " + sessionId + " releases partitions " + partitions);
  final Topology topology=getTopology();
  final List<Partition> changeSet=new ArrayList<>();
  for (  final EventTypePartition etp : partitions) {
    final Partition candidate=Stream.of(topology.getPartitions()).filter(p -> p.getKey().equals(etp)).findAny().get();
    if (sessionId.equals(candidate.getSession()) && candidate.getState() == Partition.State.REASSIGNING) {
      changeSet.add(candidate.toState(Partition.State.ASSIGNED,candidate.getNextSession(),null));
    }
  }
  if (!changeSet.isEmpty()) {
    updatePartitionsConfiguration(topology.getSessionsHash(),changeSet.toArray(new Partition[changeSet.size()]));
  }
}
