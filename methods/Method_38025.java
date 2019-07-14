/** 
 * Executes function on a string if not  {@code null}. Otherwise returns an empty string.
 */
public static String ifNotNull(final String input,final Function<String,String> stringFunction){
  if (input == null) {
    return StringPool.EMPTY;
  }
  return stringFunction.apply(input);
}
