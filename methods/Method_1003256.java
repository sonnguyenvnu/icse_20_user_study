/** 
 * Truncate the file.
 * @param newLength the new length
 */
void truncate(long newLength){
  changeLength(newLength);
  long end=MathUtils.roundUpLong(newLength,BLOCK_SIZE);
  if (end != newLength) {
    int lastPage=(int)(newLength >>> BLOCK_SIZE_SHIFT);
    byte[] d=expand(lastPage);
    byte[] d2=Arrays.copyOf(d,d.length);
    for (int i=(int)(newLength & BLOCK_SIZE_MASK); i < BLOCK_SIZE; i++) {
      d2[i]=0;
    }
    setPage(lastPage,d,d2,true);
    if (compress) {
      compressLater(lastPage);
    }
  }
}
