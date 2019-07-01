/** 
 * Get the number of bytes we should use as chunk allocation for org.apache.bookkeeper.bookie.SkipListArena. Default is 4 MB
 * @return the number of bytes to use for each chunk in the skiplist arena
 */
public int _XXXXX_(){
  return getInt(SKIP_LIST_CHUNK_SIZE_ENTRY,4096 * 1024);
}