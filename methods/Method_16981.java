/** 
 * Return a non-zero 32-bit pseudo random value. The  {@code instance} objectmay be used as part of the value.
 * @param instance an object to use if desired in choosing value.
 * @return a non-zero 32-bit pseudo random value.
 */
public static int randomHashSeed(Object instance){
  int seed=ThreadLocalRandom.current().nextInt();
  return (0 != seed) ? seed : 1;
}
