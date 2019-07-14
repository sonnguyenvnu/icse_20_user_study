@Override public void close(String groupId){
  dtxContextRegistry.destroyContext(groupId);
}
