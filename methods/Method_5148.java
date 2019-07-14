/** 
 * Returns the next chunk index or  {@link C#INDEX_UNSET} if it is not known. 
 */
public long getNextChunkIndex(){
  return chunkIndex != C.INDEX_UNSET ? chunkIndex + 1 : C.INDEX_UNSET;
}
