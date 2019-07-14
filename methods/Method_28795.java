public void assignSlotsToNode(List<Integer> targetSlots,HostAndPort targetNode){
  w.lock();
  try {
    JedisPool targetPool=nodes.get(getNodeKey(targetNode));
    if (targetPool == null) {
      setNodeIfNotExist(targetNode);
      targetPool=nodes.get(getNodeKey(targetNode));
    }
    for (    Integer slot : targetSlots) {
      slots.put(slot,targetPool);
    }
  }
  finally {
    w.unlock();
  }
}
