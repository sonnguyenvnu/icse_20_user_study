public void created(final String name) throws Exception {
  try {
    final String path=getZNodePath(name);
    zkClient.get().create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path,new byte[0]);
  }
 catch (  final KeeperException.NodeExistsException expected) {
    LOG.debug("Silently do nothing since event type has already been tracked");
  }
  timelineRegistrations.computeIfAbsent(name,n -> timelineSync.registerTimelineChangeListener(n,(etName) -> eventTypeCache.invalidate(etName)));
}
