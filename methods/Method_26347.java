/** 
 * Throws an  {@link IllegalGuardedBy} exception if the given condition is false. 
 */
public static void checkGuardedBy(boolean condition,String message){
  if (!condition) {
    throw new IllegalGuardedBy(message);
  }
}
