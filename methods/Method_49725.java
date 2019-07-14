/** 
 * Returns whether the provided Open Location Code is a padded Open Location Code, meaning that it contains less than 8 valid digits.
 * @param code The code to check.
 * @return True if it is padded.
 */
public static boolean isPadded(String code) throws IllegalArgumentException {
  return new OpenLocationCode(code).isPadded();
}
