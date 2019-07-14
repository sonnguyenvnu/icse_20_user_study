private boolean startCluster(final long appId,Map<Jedis,Jedis> clusterMap){
  final Jedis jedis=new ArrayList<Jedis>(clusterMap.keySet()).get(0);
  for (  final Jedis master : clusterMap.keySet()) {
    boolean isMeet=new IdempotentConfirmer(){
      @Override public boolean execute(){
        boolean isMeet=clusterMeet(jedis,appId,master.getClient().getHost(),master.getClient().getPort());
        if (!isMeet) {
          return false;
        }
        return true;
      }
    }
.run();
    if (!isMeet) {
      return false;
    }
    final Jedis slave=clusterMap.get(master);
    if (slave != null) {
      isMeet=new IdempotentConfirmer(){
        @Override public boolean execute(){
          boolean isMeet=clusterMeet(jedis,appId,slave.getClient().getHost(),slave.getClient().getPort());
          if (!isMeet) {
            return false;
          }
          return true;
        }
      }
.run();
      if (!isMeet) {
        return false;
      }
    }
  }
  int masterSize=clusterMap.size();
  int perSize=(int)Math.ceil(16384 / masterSize);
  int index=0;
  int masterIndex=0;
  final ArrayList<Integer> slots=new ArrayList<Integer>();
  List<Jedis> masters=new ArrayList<Jedis>(clusterMap.keySet());
  for (int slot=0; slot <= 16383; slot++) {
    slots.add(slot);
    if (index++ >= perSize || slot == 16383) {
      final int[] slotArr=new int[slots.size()];
      for (int i=0; i < slotArr.length; i++) {
        slotArr[i]=slots.get(i);
      }
      final Jedis masterJedis=masters.get(masterIndex++);
      boolean isSlot=new IdempotentConfirmer(){
        @Override public boolean execute(){
          String response=masterJedis.clusterAddSlots(slotArr);
          boolean isSlot=response != null && response.equalsIgnoreCase("OK");
          if (!isSlot) {
            return false;
          }
          return true;
        }
      }
.run();
      if (!isSlot) {
        logger.error("{}:{} set slots:{}",masterJedis.getClient().getHost(),masterJedis.getClient().getPort(),slots);
        return false;
      }
      slots.clear();
      index=0;
    }
  }
  for (  Jedis masterJedis : clusterMap.keySet()) {
    final Jedis slaveJedis=clusterMap.get(masterJedis);
    if (slaveJedis == null) {
      continue;
    }
    final String nodeId=getClusterNodeId(masterJedis);
    boolean isReplicate=new IdempotentConfirmer(){
      @Override public boolean execute(){
        try {
          TimeUnit.SECONDS.sleep(2);
        }
 catch (        Exception e) {
          logger.error(e.getMessage(),e);
        }
        String response=null;
        try {
          response=slaveJedis.clusterReplicate(nodeId);
        }
 catch (        Exception e) {
          logger.error(e.getMessage(),e);
        }
        boolean isReplicate=response != null && response.equalsIgnoreCase("OK");
        if (!isReplicate) {
          try {
            TimeUnit.SECONDS.sleep(2);
          }
 catch (          Exception e) {
            logger.error(e.getMessage(),e);
          }
          return false;
        }
        return true;
      }
    }
.run();
    if (!isReplicate) {
      logger.error("{}:{} set replicate:{}",slaveJedis.getClient().getHost(),slaveJedis.getClient().getPort());
      return false;
    }
  }
  return true;
}
