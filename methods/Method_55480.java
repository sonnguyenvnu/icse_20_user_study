/** 
 * Ensures that the specified pointer is not  {@code NULL} (0L).
 * @param pointer the pointer to check
 * @throws NullPointerException if {@code pointer} is {@code NULL}
 */
public static long check(long pointer){
  if (pointer == NULL) {
    throw new NullPointerException();
  }
  return pointer;
}
