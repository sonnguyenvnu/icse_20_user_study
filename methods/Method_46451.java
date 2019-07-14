@Override public void setLcnConnection(String groupId,LcnConnectionProxy connectionProxy){
  attachmentCache.attach(groupId,LcnConnectionProxy.class.getName(),connectionProxy);
}
