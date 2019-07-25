@Override public boolean regenerate(Region region,EditSession editSession){
  BaseBlock[] history=new BaseBlock[16 * 16 * (getMaxY() + 1)];
  for (  BlockVector2 chunk : region.getChunks()) {
    BlockVector3 min=BlockVector3.at(chunk.getBlockX() * 16,0,chunk.getBlockZ() * 16);
    for (int x=0; x < 16; ++x) {
      for (int y=0; y < (getMaxY() + 1); ++y) {
        for (int z=0; z < 16; ++z) {
          BlockVector3 pt=min.add(x,y,z);
          int index=y * 16 * 16 + z * 16 + x;
          history[index]=editSession.getFullBlock(pt);
        }
      }
    }
    try {
      getWorld().regenerateChunk(chunk.getBlockX(),chunk.getBlockZ());
    }
 catch (    Throwable t) {
      logger.warn("Chunk generation via Bukkit raised an error",t);
    }
    for (int x=0; x < 16; ++x) {
      for (int y=0; y < (getMaxY() + 1); ++y) {
        for (int z=0; z < 16; ++z) {
          BlockVector3 pt=min.add(x,y,z);
          int index=y * 16 * 16 + z * 16 + x;
          if (!region.contains(pt)) {
            editSession.smartSetBlock(pt,history[index]);
          }
 else {
            editSession.getChangeSet().add(new BlockChange(pt,history[index],editSession.getFullBlock(pt)));
          }
        }
      }
    }
  }
  return true;
}
