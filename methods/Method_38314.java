/** 
 * Appends chunk to the list. Chunks <b>must</b> be added using this method.
 */
public DbSqlBuilder addChunk(final SqlChunk chunk){
  if (lastChunk == null) {
    lastChunk=firstChunk=chunk;
  }
 else {
    chunk.insertChunkAfter(lastChunk);
    lastChunk=chunk;
  }
  totalChunks++;
  return this;
}
