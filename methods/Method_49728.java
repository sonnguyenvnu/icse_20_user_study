/** 
 * Returns if the code is a valid short Open Location Code.
 * @param code The code to check.
 * @return True if it is a valid short code.
 */
public static boolean isShortCode(String code){
  try {
    return new OpenLocationCode(code).isShort();
  }
 catch (  IllegalArgumentException e) {
    return false;
  }
}
