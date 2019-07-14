@Override public boolean createRunNode(AppDesc appDesc,String host,Integer port,int maxMemory,boolean isCluster){
  return runInstance(appDesc,host,port,maxMemory,isCluster);
}
