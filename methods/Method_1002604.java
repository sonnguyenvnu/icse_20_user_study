/** 
 * Code to fetch an instance of  {@link java.nio.charset.Charset} corresponding to the given encoding.
 * @param encoding as a string name (eg. UTF-8).
 * @return the code to fetch the associated Charset.
 */
public static String charset(final String encoding){
  final String charsetName=STD_CHARSETS.get(encoding);
  if (charsetName != null) {
    return "java.nio.charset.StandardCharsets." + charsetName;
  }
 else {
    return "java.nio.charset.Charset.forName(\"" + encoding + "\")";
  }
}
