/** 
 * Returns <code>true</code> if type is some string-like type: CHAR, VARCHAR.
 */
public static boolean isStringType(final int type){
  return (type == Types.VARCHAR) || (type == Types.CHAR);
}
