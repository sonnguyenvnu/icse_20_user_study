@Override public void update(Object args,Observable observable){
  EventType type=(EventType)args;
  if (!(observable instanceof ClusterDeleteEvent) || type != EventType.DELETE) {
    logger.info("[update] observable object not ShardDeleteEvent, skip. observable: {}, args: {}",observable.getClass().getName(),args.getClass().getName());
    return;
  }
  ClusterDeleteEvent clusterDeleteEvent=(ClusterDeleteEvent)observable;
  for (  ShardEvent shardEvent : clusterDeleteEvent.getShardEvents()) {
    shardEvent.onEvent();
  }
}
