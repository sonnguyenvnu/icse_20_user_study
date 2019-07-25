@Override public void move(int slotId,int fromServer,int toServer){
  try {
    lock.writeLock().lock();
    if (serverMap.get(fromServer) == null) {
      logger.error("[fromServer not Found]" + fromServer);
      return;
    }
    slotsMap.put(slotId,new SlotInfo(toServer));
    serverMap.get(fromServer).remove(slotId);
    Set<Integer> toSlots=MapUtils.getOrCreate(serverMap,toServer,new ObjectFactory<Set<Integer>>(){
      @Override public Set<Integer> create(){
        return new HashSet<>();
      }
    }
);
    toSlots.add(slotId);
  }
  finally {
    lock.writeLock().unlock();
  }
}
