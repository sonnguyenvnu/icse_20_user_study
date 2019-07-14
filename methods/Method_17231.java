/** 
 * Returns the probe value for the current thread. Duplicated from ThreadLocalRandom because of packaging restrictions.
 */
static final int getProbe(){
  return UnsafeAccess.UNSAFE.getInt(Thread.currentThread(),PROBE);
}
