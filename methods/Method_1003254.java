/** 
 * Compress the data in a byte array.
 * @param page which page to compress
 */
void compress(int page){
  byte[] old=getPage(page);
  if (old == null || old.length != BLOCK_SIZE) {
    return;
  }
synchronized (LZF) {
    int len=LZF.compress(old,BLOCK_SIZE,BUFFER,0);
    if (len <= BLOCK_SIZE) {
      byte[] d=Arrays.copyOf(BUFFER,len);
      setPage(page,old,d,false);
    }
  }
}
