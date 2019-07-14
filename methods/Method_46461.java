@Override public void setRollbackOnly(String groupId){
  this.attachmentCache.attach(groupId,"rollback-only",true);
}
