/** 
 * Throws  {@link IllegalStateException} if {@code expression} evaluates to false.
 * @param expression The expression to evaluate.
 * @throws IllegalStateException If {@code expression} is false.
 */
public static void checkState(boolean expression){
  if (ExoPlayerLibraryInfo.ASSERTIONS_ENABLED && !expression) {
    throw new IllegalStateException();
  }
}
