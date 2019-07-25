/** 
 * Returns a random value in the range [1 - range]. If range is 0, 1 will be returned. If range is negative, an exception will be thrown 
 */
public static long random(long range){
  return range == 0 ? 1 : ThreadLocalRandom.current().nextLong(range) + 1;
}
