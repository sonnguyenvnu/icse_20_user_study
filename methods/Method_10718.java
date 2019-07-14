/** 
 * string?outputStream???
 * @param string      ???
 * @param charsetName ????
 * @return ???
 */
public static OutputStream string2OutputStream(String string,String charsetName){
  if (string == null || isNullString(charsetName)) {
    return null;
  }
  try {
    return bytes2OutputStream(string.getBytes(charsetName));
  }
 catch (  UnsupportedEncodingException e) {
    e.printStackTrace();
    return null;
  }
}
