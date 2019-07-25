@Override public void update(Object args,Observable observable){
  EventType type=(EventType)args;
  if (!(observable instanceof ShardDeleteEvent) || type != EventType.DELETE) {
    logger.info("[update] observable object not ShardDeleteEvent, skip. observable: {}, args: {}",observable.getClass().getName(),args.getClass().getName());
    return;
  }
  ShardDeleteEvent shardDeleteEvent=(ShardDeleteEvent)observable;
  sentinelManager.removeShardSentinelMonitors(shardDeleteEvent);
}
