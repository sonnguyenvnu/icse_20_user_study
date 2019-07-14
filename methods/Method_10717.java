/** 
 * string?inputStream???
 * @param string      ???
 * @param charsetName ????
 * @return ???
 */
public static InputStream string2InputStream(String string,String charsetName){
  if (string == null || isNullString(charsetName)) {
    return null;
  }
  try {
    return new ByteArrayInputStream(string.getBytes(charsetName));
  }
 catch (  UnsupportedEncodingException e) {
    e.printStackTrace();
    return null;
  }
}
