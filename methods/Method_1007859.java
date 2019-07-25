@Override public boolean regenerate(Region region,EditSession editSession){
  ChunkManager provider=getWorld().getChunkManager();
  if (!(provider instanceof ServerChunkManager)) {
    return false;
  }
  File saveFolder=Files.createTempDir();
  saveFolder.deleteOnExit();
  try {
    ServerWorld originalWorld=(ServerWorld)getWorld();
    MinecraftServer server=originalWorld.getServer();
    WorldSaveHandler saveHandler=new WorldSaveHandler(saveFolder,originalWorld.getSaveHandler().getWorldDir().getName(),server,server.getDataFixer());
    World freshWorld=new ServerWorld(server,server.getWorkerExecutor(),saveHandler,originalWorld.getLevelProperties(),originalWorld.dimension.getType(),originalWorld.getProfiler(),new NoOpChunkStatusListener());
    CuboidRegion expandedPreGen=new CuboidRegion(region.getMinimumPoint().subtract(16,0,16),region.getMaximumPoint().add(16,0,16));
    for (    BlockVector2 chunk : expandedPreGen.getChunks()) {
      freshWorld.getChunk(chunk.getBlockX(),chunk.getBlockZ());
    }
    FabricWorld from=new FabricWorld(freshWorld);
    for (    BlockVector3 vec : region) {
      editSession.setBlock(vec,from.getFullBlock(vec));
    }
  }
 catch (  MaxChangedBlocksException e) {
    throw new RuntimeException(e);
  }
 finally {
    saveFolder.delete();
  }
  return true;
}
