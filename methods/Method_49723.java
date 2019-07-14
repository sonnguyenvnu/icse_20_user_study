/** 
 * Returns whether the provided Open Location Code is a short Open Location Code.
 * @param code The code to check.
 * @return True if it is short.
 */
public static boolean isShort(String code) throws IllegalArgumentException {
  return new OpenLocationCode(code).isShort();
}
