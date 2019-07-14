/** 
 * Helper method that transforms provided string into it's byte representation using ASCII encoding.
 * @param value the string to use
 * @return byte array representing ascii encoded value
 */
public static byte[] asciiBytes(String value){
  Preconditions.checkNotNull(value);
  try {
    return value.getBytes("ASCII");
  }
 catch (  UnsupportedEncodingException uee) {
    throw new RuntimeException("ASCII not found!",uee);
  }
}
