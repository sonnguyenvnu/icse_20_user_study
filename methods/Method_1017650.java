/** 
 * Parses a boolean value from the byte array.
 * @param bytes The byte array to parse.
 * @param idx The starting index to read from bytes.
 * @return parsed boolean value.
 */
public static boolean bool(byte[] bytes,int idx){
  return bytes[idx] == 1;
}
