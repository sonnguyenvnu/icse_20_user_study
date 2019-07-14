public void discoverClusterSlots(Jedis jedis){
  w.lock();
  try {
    this.slots.clear();
    List<Object> slots=jedis.clusterSlots();
    for (    Object slotInfoObj : slots) {
      List<Object> slotInfo=(List<Object>)slotInfoObj;
      if (slotInfo.size() <= 2) {
        continue;
      }
      List<Integer> slotNums=getAssignedSlotArray(slotInfo);
      List<Object> hostInfos=(List<Object>)slotInfo.get(2);
      if (hostInfos.size() <= 0) {
        continue;
      }
      HostAndPort targetNode=generateHostAndPort(hostInfos);
      setNodeIfNotExist(targetNode);
      assignSlotsToNode(slotNums,targetNode);
    }
  }
  finally {
    w.unlock();
  }
}
