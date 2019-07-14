/** 
 * Pseudo-randomly advances and records the given probe value for the given thread. Duplicated from ThreadLocalRandom because of packaging restrictions.
 */
static final int advanceProbe(int probe){
  probe^=probe << 13;
  probe^=probe >>> 17;
  probe^=probe << 5;
  UnsafeAccess.UNSAFE.putInt(Thread.currentThread(),PROBE,probe);
  return probe;
}
