/** 
 * Checks whether this denotes a location on the classpath.
 * @return {@code true} if it does, {@code false} if it doesn't.
 */
public boolean isClassPath(){
  return CLASSPATH_PREFIX.equals(prefix);
}
