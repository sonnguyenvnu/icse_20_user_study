/** 
 * Helper method that transforms provided string into its byte representation using ASCII encoding
 * @param value bytes value
 * @return byte array representing ascii encoded value
 */
private static byte[] asciiBytes(String value){
  try {
    return value.getBytes("ASCII");
  }
 catch (  UnsupportedEncodingException uee) {
    throw new RuntimeException("ASCII not found!",uee);
  }
}
