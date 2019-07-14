public void clusterCountKeysInSlot(final int slot){
  cluster(Protocol.CLUSTER_COUNTKEYINSLOT,String.valueOf(slot));
}
