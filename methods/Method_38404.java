/** 
 * Returns <code>true</code> if type is some integer-like type: INTEGER, SMALLINT, TINYINT, BIT.
 */
public static boolean isIntegerType(final int type){
  return (type == Types.INTEGER) || (type == Types.SMALLINT) || (type == Types.TINYINT) || (type == Types.BIT);
}
