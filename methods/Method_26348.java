/** 
 * Throws an  {@link IllegalGuardedBy} exception if the given condition is false. 
 */
public static void checkGuardedBy(boolean condition,String formatString,Object... formatArgs){
  if (!condition) {
    throw new IllegalGuardedBy(String.format(formatString,formatArgs));
  }
}
