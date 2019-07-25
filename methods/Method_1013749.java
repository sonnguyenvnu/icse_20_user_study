private boolean valid(ReplicationStoreMeta meta){
  if (meta == null) {
    return false;
  }
  if (meta.getReplId() == null || meta.getReplId().length() != RedisProtocol.RUN_ID_LENGTH) {
    return false;
  }
  if (meta.getBeginOffset() == null) {
    return false;
  }
  if (meta.getMasterAddress() == null) {
    return false;
  }
  return true;
}
