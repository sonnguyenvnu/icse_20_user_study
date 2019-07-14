/** 
 * Returns `true` if the boolean is `null` or `false`, and `true` otherwise.
 */
public static boolean isFalse(final @Nullable Boolean bool){
  return !isTrue(bool);
}
