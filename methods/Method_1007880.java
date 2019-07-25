@Override public boolean regenerate(Region region,EditSession editSession){
  AbstractChunkProvider provider=getWorld().getChunkProvider();
  if (!(provider instanceof ServerChunkProvider)) {
    return false;
  }
  File saveFolder=Files.createTempDir();
  saveFolder.deleteOnExit();
  try {
    ServerWorld originalWorld=(ServerWorld)getWorld();
    MinecraftServer server=originalWorld.getServer();
    SaveHandler saveHandler=new SaveHandler(saveFolder,originalWorld.getSaveHandler().getWorldDirectory().getName(),server,server.getDataFixer());
    World freshWorld=new ServerWorld(server,server.getBackgroundExecutor(),saveHandler,originalWorld.getWorldInfo(),originalWorld.dimension.getType(),originalWorld.getProfiler(),new NoOpChunkStatusListener());
    CuboidRegion expandedPreGen=new CuboidRegion(region.getMinimumPoint().subtract(16,0,16),region.getMaximumPoint().add(16,0,16));
    for (    BlockVector2 chunk : expandedPreGen.getChunks()) {
      freshWorld.getChunk(chunk.getBlockX(),chunk.getBlockZ());
    }
    ForgeWorld from=new ForgeWorld(freshWorld);
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
