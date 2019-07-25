/** 
 * Invoked from ClassPool#compress(). It releases the class files that have not been recently used if they are unmodified.
 */
@Override void compress(){
  if (getCount < GET_THRESHOLD)   if (!isModified() && ClassPool.releaseUnmodifiedClassFile)   removeClassFile();
 else   if (isFrozen() && !wasPruned)   saveClassFile();
  getCount=0;
}
