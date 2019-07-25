/** 
 * Converts bytes into normalized JSON representation.
 * @param bytes source representation
 * @return normalized representation
 */
public static byte[] normalize(byte[] bytes){
  return parse(bytes,new JSONByteArrayTarget());
}
