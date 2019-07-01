private void _XXXXX_(){
  checkNotNull(zkServers,"No zk servers provided.");
  checkArgument(conectionTimeoutMs > 0,"Invalid connection timeout : %d",conectionTimeoutMs);
  checkArgument(sessionTimeoutMs > 0,"Invalid session timeout : %d",sessionTimeoutMs);
  checkNotNull(statsLogger,"No stats logger provided.");
  checkArgument(zkAclIdSet,"Zookeeper acl id not set.");
}