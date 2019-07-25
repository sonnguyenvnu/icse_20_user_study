@Override public Partition[] apply(final Collection<Session> sessions,final Partition[] currentPartitions){
  final List<String> activeSessions=sessions.stream().map(Session::getId).collect(Collectors.toList());
  final List<Partition> partitionsLeft=Lists.newArrayList(currentPartitions);
  final List<Partition> changedPartitions=new ArrayList<>();
  final List<Session> sessionsWithSpecifiedPartitions=sessions.stream().filter(s -> !s.getRequestedPartitions().isEmpty()).collect(Collectors.toList());
  for (  final Session session : sessionsWithSpecifiedPartitions) {
    for (    final EventTypePartition requestedPartition : session.getRequestedPartitions()) {
      final Partition partition=partitionsLeft.stream().filter(p -> p.getKey().equals(requestedPartition)).findFirst().orElseThrow(() -> new RebalanceConflictException("Two existing sessions request the same partition: " + requestedPartition));
      partitionsLeft.remove(partition);
      if (!session.getId().equals(partition.getSession())) {
        final Partition movedPartition=partition.moveToSessionId(session.getId(),activeSessions);
        changedPartitions.add(movedPartition);
      }
    }
  }
  final List<Session> autoBalanceSessions=sessions.stream().filter(s -> s.getRequestedPartitions().isEmpty()).collect(Collectors.toList());
  if (!autoBalanceSessions.isEmpty() && !partitionsLeft.isEmpty()) {
    final Partition[] partitionsChangedByAutoRebalance=rebalanceByWeight(autoBalanceSessions,partitionsLeft.toArray(new Partition[partitionsLeft.size()]));
    changedPartitions.addAll(Arrays.asList(partitionsChangedByAutoRebalance));
  }
  return changedPartitions.toArray(new Partition[changedPartitions.size()]);
}
