public void clusterGetKeysInSlot(final int slot,final int count){
  final int[] args=new int[]{slot,count};
  cluster(Protocol.CLUSTER_GETKEYSINSLOT,args);
}
