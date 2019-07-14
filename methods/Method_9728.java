/** 
 * Checks the specified title exists in the specified title set.
 * @param titles the specified title set
 * @param title  the specified title to check
 * @return {@code true} if exists, returns {@code false} otherwise
 */
private static boolean exists(final Set<String> titles,final String title){
  for (  final String setTitle : titles) {
    if (setTitle.equalsIgnoreCase(title)) {
      return true;
    }
  }
  return false;
}
