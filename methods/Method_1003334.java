/** 
 * Clear the map and reset the level to the specified value.
 * @param newLevel the new level
 */
protected void reset(int newLevel){
  if (newLevel > 30) {
    throw new IllegalStateException("exceeded max size of hash table");
  }
  size=0;
  level=newLevel;
  len=2 << level;
  mask=len - 1;
  minSize=(int)((1 << level) * MAX_LOAD / 100);
  maxSize=(int)(len * MAX_LOAD / 100);
  deletedCount=0;
  maxDeleted=20 + len / 2;
}
