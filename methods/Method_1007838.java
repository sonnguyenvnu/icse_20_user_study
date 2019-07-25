/** 
 * Restores to world.
 * @throws MaxChangedBlocksException
 */
public void restore() throws MaxChangedBlocksException {
  missingChunks=new ArrayList<>();
  errorChunks=new ArrayList<>();
  for (  Map.Entry<BlockVector2,ArrayList<BlockVector3>> entry : neededChunks.entrySet()) {
    BlockVector2 chunkPos=entry.getKey();
    Chunk chunk;
    try {
      chunk=chunkStore.getChunk(chunkPos,editSession.getWorld());
      for (      BlockVector3 pos : entry.getValue()) {
        try {
          editSession.setBlock(pos,chunk.getBlock(pos));
        }
 catch (        DataException e) {
        }
      }
    }
 catch (    MissingChunkException me) {
      missingChunks.add(chunkPos);
    }
catch (    IOException|DataException me) {
      errorChunks.add(chunkPos);
      lastErrorMessage=me.getMessage();
    }
  }
}
