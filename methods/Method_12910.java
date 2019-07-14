/** 
 * Return whether the given throwable is a checked exception: that is, neither a RuntimeException nor an Error.
 * @param ex the throwable to check
 * @return whether the throwable is a checked exception
 * @see Exception
 * @see RuntimeException
 * @see Error
 */
public static boolean isCheckedException(Throwable ex){
  return !(ex instanceof RuntimeException || ex instanceof Error);
}
