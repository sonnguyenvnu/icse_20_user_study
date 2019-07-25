public int utimens(String s,Timespec[] timespecs){
  ensureNotClosed();
  int aDefault=-ErrorCodes.ENOENT();
  Optional<PeergosStat> parentOpt=getParentByPath(s);
  if (!parentOpt.isPresent())   return aDefault;
  return applyIfPresent(s,(stat) -> {
    Timespec access=timespecs[0], modified=timespecs[1];
    long epochSeconds=modified.tv_sec.longValue();
    Instant instant=Instant.ofEpochSecond(epochSeconds);
    LocalDateTime lastModified=LocalDateTime.ofInstant(instant,ZoneId.systemDefault());
    FileProperties updated=stat.properties.withModified(lastModified);
    try {
      boolean isUpdated=stat.treeNode.setProperties(updated,context.network,Optional.of(parentOpt.get().treeNode)).get();
      return isUpdated ? 0 : -ErrorCodes.ENOENT();
    }
 catch (    Exception ex) {
      LOG.log(Level.WARNING,ex.getMessage(),ex);
      return -ErrorCodes.ENOENT();
    }
  }
,aDefault);
}
