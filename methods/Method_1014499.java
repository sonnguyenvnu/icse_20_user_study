@Override public void handle(GameEvent event){
  World world=((WorldUpdateEvent)event).getWorld();
  if (world.getDimension().startsWith("w")) {
    if (world.findObjects(BiomassBlob.class).size() < blobThreshold) {
      if (!worldWaitMap.containsKey(world) || worldWaitMap.get(world) == 0L) {
        worldWaitMap.put(world,GameServer.INSTANCE.getGameUniverse().getTime() + waitTime);
      }
 else {
        long waitUntil=worldWaitMap.get(world);
        if (GameServer.INSTANCE.getGameUniverse().getTime() >= waitUntil) {
          ArrayList<BiomassBlob> newBlobs=WorldUtils.generateBlobs(world,minBlobCount,maxBlobCount,blobYield);
          for (          BiomassBlob blob : newBlobs) {
            world.addObject(blob);
            world.incUpdatable();
          }
          worldWaitMap.replace(world,0L);
        }
      }
    }
  }
}
