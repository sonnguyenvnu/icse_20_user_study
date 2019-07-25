@Override public void refresh(int... slotIds) throws ClusterException {
  String slotsPath=MetaZkConfig.getMetaServerSlotsPath();
  CuratorFramework client=zkClient.get();
  Map<Integer,SlotInfo> slotsInfo=new HashMap<>();
  for (  int slotId : slotIds) {
    byte[] slotContent=new byte[0];
    try {
      slotContent=client.getData().forPath(slotsPath + "/" + String.valueOf(slotId));
    }
 catch (    Exception e) {
      throw new ClusterException("get data for:" + slotId,e);
    }
    SlotInfo slotInfo=Codec.DEFAULT.decode(slotContent,SlotInfo.class);
    slotsInfo.put(slotId,slotInfo);
  }
  try {
    lock.writeLock().lock();
    for (    Entry<Integer,SlotInfo> entry : slotsInfo.entrySet()) {
      Integer slotId=entry.getKey();
      SlotInfo slotInfo=entry.getValue();
      SlotInfo oldSlotInfo=slotsMap.get(slotId);
      slotsMap.put(slotId,slotInfo);
      if (oldSlotInfo != null) {
        Set<Integer> oldServerSlot=serverMap.get(oldSlotInfo.getServerId());
        if (oldServerSlot != null) {
          oldServerSlot.remove(slotId);
        }
      }
      getOrCreateServerMap(serverMap,slotInfo.getServerId()).add(slotId);
    }
  }
  finally {
    lock.writeLock().unlock();
  }
}
