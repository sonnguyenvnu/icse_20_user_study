@Override public void run(){
  PlayerChunkMapEntryBridge_Forge spongePlayerChunkMapEntry=(PlayerChunkMapEntryBridge_Forge)this.playerChunkMapEntry;
  Chunk chunk=this.playerChunkMap.getWorldServer().getChunkProvider().getLoadedChunk(this.playerChunkMapEntry.pos.x,this.playerChunkMapEntry.pos.z);
  if (chunk != null) {
    spongePlayerChunkMapEntry.forgeBridge$setChunk(chunk);
    return;
  }
  spongePlayerChunkMapEntry.forgeBridge$setLoading(false);
}
