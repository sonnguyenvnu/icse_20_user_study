/** 
 * Throws  {@link IllegalArgumentException} if {@code expression} evaluates to false.
 * @param expression The expression to evaluate.
 * @throws IllegalArgumentException If {@code expression} is false.
 */
public static void checkArgument(boolean expression){
  if (ExoPlayerLibraryInfo.ASSERTIONS_ENABLED && !expression) {
    throw new IllegalArgumentException();
  }
}
