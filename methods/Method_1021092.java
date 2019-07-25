public void whitelist(final String name,final Type type) throws RuntimeException {
  try {
    final CuratorFramework curator=zooKeeperHolder.get();
    final String path=createBlacklistEntryPath(name,type);
    if (curator.checkExists().forPath(path) != null) {
      curator.delete().forPath(path);
      final BlacklistEntry entry=new BlacklistEntry(type,name);
      auditLogPublisher.publish(Optional.of(entry),Optional.empty(),NakadiAuditLogPublisher.ResourceType.BLACKLIST_ENTRY,NakadiAuditLogPublisher.ActionType.DELETED,entry.getId());
    }
  }
 catch (  final Exception e) {
    throw new RuntimeException("Issue occurred while deleting node from zk",e);
  }
}
