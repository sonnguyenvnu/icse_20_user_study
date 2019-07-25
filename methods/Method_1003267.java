/** 
 * Unlock the file.
 */
synchronized void unlock(){
  if (isLockedExclusive) {
    isLockedExclusive=false;
  }
 else {
    sharedLockCount=Math.max(0,sharedLockCount - 1);
  }
}
