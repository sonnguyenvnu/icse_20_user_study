/** 
 * Truncate the file.
 * @param newLength the new length
 */
void truncate(long newLength){
  rwLock.writeLock().lock();
  try {
    changeLength(newLength);
    long end=MathUtils.roundUpLong(newLength,BLOCK_SIZE);
    if (end != newLength) {
      int lastPage=(int)(newLength >>> BLOCK_SIZE_SHIFT);
      ByteBuffer d=expandPage(lastPage);
      for (int i=(int)(newLength & BLOCK_SIZE_MASK); i < BLOCK_SIZE; i++) {
        d.put(i,(byte)0);
      }
      if (compress) {
        addToCompressLaterCache(lastPage);
      }
    }
  }
  finally {
    rwLock.writeLock().unlock();
  }
}
