/** 
 * Checks if any of the specified functions pointers is  {@code NULL}.
 * @param functions the function pointers to check
 * @return true if all function pointers are valid, false otherwise.
 */
public static boolean checkFunctions(long... functions){
  for (  long pointer : functions) {
    if (pointer == NULL) {
      return false;
    }
  }
  return true;
}
