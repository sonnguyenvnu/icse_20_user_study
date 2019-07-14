/** 
 * Returns the arena index for the current thread. 
 */
static int index(){
  int probe=UnsafeAccess.UNSAFE.getInt(Thread.currentThread(),PROBE);
  if (probe == 0) {
    ThreadLocalRandom.current();
    probe=UnsafeAccess.UNSAFE.getInt(Thread.currentThread(),PROBE);
  }
  return (probe & ARENA_MASK);
}
