public List<ACL> _XXXXX_(){
  if (Credentials.NONE == credentials) {
    return ZooDefs.Ids.OPEN_ACL_UNSAFE;
  }
 else {
    return DistributedLogConstants.EVERYONE_READ_CREATOR_ALL;
  }
}